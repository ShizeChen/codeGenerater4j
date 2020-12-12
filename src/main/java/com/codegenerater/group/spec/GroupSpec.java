package com.codegenerater.group.spec;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 定义模板组
 *
 * @author wuhuilin
 * Date 2020-9-14.
 */
public class GroupSpec {
    private String id;
    private List<String> templateIds = Lists.newArrayList();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTemplateIds() {
        return templateIds;
    }

    public void setTemplateIds(List<String> templateIds) {
        this.templateIds = templateIds;
    }

    public void addTemplate(String templateId) {
        if (templateIds.contains(templateId)) {
            return;
        }
        templateIds.add(templateId);
    }
}
