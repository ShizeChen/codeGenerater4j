package com.codegenerater.templates;


import com.codegenerater.templates.spec.TemplateSpec;

/**
 * 模板实例
 *
 * @author XD.Wang
 * Date 2020-8-2.
 */
public class TemplateInstant {

    private TemplateSpec templateSpec;

    private String content;

    public TemplateSpec getTemplateSpec() {
        return templateSpec;
    }

    public void setTemplateSpec(TemplateSpec templateSpec) {
        this.templateSpec = templateSpec;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
