package com.codegenerater.templates.spec;


import com.codegenerater.common.FileSetting;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Objects;

/**
 * 定义模板
 *
 * @author wuhuilin
 * Date 2020-8-23.
 */
public class TemplateSpec {

    private String id;

    private FileSetting fileSetting;

    private String path;

    private final Map<String, Object> attrs = Maps.newHashMap();

    private String description;

    private boolean isEmbed = false;

    public static TemplateSpec of(FileSetting fileSetting, String path, String description) {
        TemplateSpec spec = new TemplateSpec();
        spec.setPath(path);
        spec.setDescription(description);
        spec.setFileSetting(fileSetting);
        return spec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FileSetting getFileSetting() {
        return fileSetting;
    }

    public void setFileSetting(FileSetting fileSetting) {
        this.fileSetting = fileSetting;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEmbed() {
        return isEmbed;
    }

    public void setEmbed(boolean embed) {
        isEmbed = embed;
    }

    public TemplateSpec addAttr(String name, Object val) {
        attrs.put(name, val);
        return this;
    }

    public Map<String, Object> getAttrs() {
        return attrs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateSpec that = (TemplateSpec) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
