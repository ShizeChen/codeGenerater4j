package com.codegenerater.bootstrap;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.log.StaticLog;
import com.codegenerater.common.GlobalConfig;
import com.codegenerater.container.GlobalContext;
import com.codegenerater.exporter.Exporter;
import com.codegenerater.extract.Extractor;
import com.codegenerater.model.Model;
import com.codegenerater.render.View;
import com.codegenerater.render.executor.*;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 启动器
 * 根据外部配置串联框架中的各组件，并定义了内容生成的主干逻辑
 *
 * @author XD.Wang
 * Date 2020-8-2.
 */
public abstract class Bootstrap {

    /**
     * 基于模板来生成内容
     *
     * @param templateIds 模板ID
     */
    public void execute(String... templateIds) {
        execute(Lists.newArrayList(templateIds));
    }

    public void executeByGroup(String groupId) {
        try {
            StaticLog.info("start to generate content, groupId -> {}", groupId);
            Preconditions.checkArgument(!Strings.isNullOrEmpty(groupId), "registered group id required！");
            GlobalConfig config = configureContext();
            contextCheckBeforeExecute(config);
            GlobalContext context = GlobalContext.create(config);
            Model model = context.calByComponent(config.getExtractor(), Extractor::extract);
            List<View> views = renderViews(model, context.getTemplateIdsByGroupId(groupId));
            context.doWithComponent(config.getExporter(), exporter -> exporter.export(views));
            StaticLog.info("generate content success! check your file at " + context.calByComponent(config.getExporter(), Exporter::getOutputPath));
        } catch (Exception e) {
            StaticLog.error(e, "generate content failed! ");
        }
    }

    /**
     * 基于模板来生成内容
     *
     * @param templateIds 模板ID
     */
    public void execute(List<String> templateIds) {
        try {
            StaticLog.info("start to generate content, templateId -> {}", templateIds);
            Preconditions.checkArgument(CollectionUtil.isNotEmpty(templateIds), "registered template id required！");
            GlobalConfig config = configureContext();
            contextCheckBeforeExecute(config);
            GlobalContext context = GlobalContext.create(config);
            Model model = context.calByComponent(config.getExtractor(), Extractor::extract);
            List<View> views = renderViews(model, templateIds);
            context.doWithComponent(config.getExporter(), exporter -> exporter.export(views));
            StaticLog.info("generate content success! check your file at " + context.calByComponent(config.getExporter(), Exporter::getOutputPath));
        } catch (Exception e) {
            StaticLog.error(e, "generate content failed! ");
        }
    }

    /**
     * 利用执行器去按序渲染模板 1 -> N
     *
     * @param model 数据
     * @return 模型
     */
    private List<View> renderViews(Model model, List<String> templateIds) {
        RenderWrapper renderHeader = CodeRendersChainManager.getManager().chaining(templateIds);
        ConfigurableRendersExecutor rendersExecutor = new ConfigurableRendersExecutor(renderHeader);
        HandlerRequest req = new HandlerRequest();
        req.setModel(model);
        HandlerResponse resp = new HandlerResponse();
        rendersExecutor.execute(req, resp);
        return resp.getViews();
    }

    private void contextCheckBeforeExecute(GlobalConfig config) {
        Preconditions.checkNotNull(config, "global config cant be null！");
        Preconditions.checkNotNull(config.getWorkspacePath(), "work space path must be set！");
        Preconditions.checkNotNull(config.getExporter(), "require a exporter！");
        Preconditions.checkNotNull(config.getExtractor(), "require a extractor！");
        Preconditions.checkNotNull(config.getCompiler(), "require a compiler！");
    }

    /**
     * 获取上下文
     *
     * @return 获取上下文
     */
    protected abstract GlobalConfig configureContext();

}
