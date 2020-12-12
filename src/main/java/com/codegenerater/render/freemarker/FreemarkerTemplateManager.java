package com.codegenerater.render.freemarker;

import com.codegenerater.io.NioTextFileAccessor;
import com.codegenerater.templates.spec.TemplateSpec;
import com.codegenerater.util.GlobalContextHolder;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.util.Set;

/**
 * 基于TEMPLATE-SPEC去加载模板
 *
 * @author created by XD.Wang
 * Date 2020/9/4.
 */
public class FreemarkerTemplateManager extends StringTemplateLoader {

    private final Configuration configuration;

    private static volatile FreemarkerTemplateManager MANAGER;

    public static FreemarkerTemplateManager getManager() {
        if (MANAGER == null) {
            MANAGER = new FreemarkerTemplateManager();
        }
        return MANAGER;
    }

    public FreemarkerTemplateManager() {
        this.configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("UTF-8");
        initStringTemplateLoader();
        configuration.setTemplateLoader(this);
        configuration.setTemplateExceptionHandler((te, env, out) -> {

        });
    }

    public void initStringTemplateLoader() {
        Set<TemplateSpec> allSpec = GlobalContextHolder.globalContext.getAllTemplates();
        allSpec.forEach(spec -> {
            String content = NioTextFileAccessor.loadEmbedFile(spec.getPath());
            super.putTemplate(spec.getId(), content);
        });
    }

    public Template getTemplate(String templateId) throws IOException {
        return configuration.getTemplate(templateId);
    }

}