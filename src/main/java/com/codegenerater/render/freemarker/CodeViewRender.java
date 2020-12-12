package com.codegenerater.render.freemarker;

import cn.hutool.core.util.StrUtil;
import com.codegenerater.model.Model;
import com.codegenerater.model.TableModel;
import com.codegenerater.render.View;
import com.codegenerater.render.views.CodeView;
import com.google.common.base.Preconditions;

public class CodeViewRender extends FreemarkerRender {

    public CodeViewRender(String templateId) {
        super(templateId);
    }

    @Override
    protected void processModelBeforeRender(Model model) {
        super.processModelBeforeRender(model);
    }

    @Override
    protected View createView(String content, Model model) {
        Preconditions.checkArgument(StrUtil.isNotBlank(content));
        Preconditions.checkArgument(model instanceof TableModel);
        CodeView codeView = new CodeView();
        codeView.setContent(content);
        codeView.setFileSetting(templateInstant.getTemplateSpec().getFileSetting());
        codeView.setName(model.getName());
        return codeView;
    }

}
