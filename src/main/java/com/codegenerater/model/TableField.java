package com.codegenerater.model;

import com.codegenerater.common.FiledType;
import com.codegenerater.util.CodeStyle;

import java.util.Objects;

/**
 * @description: 表字段属性
 * @author: chenshize02
 * @create: 2020-12-12 16:22
 **/
public class TableField {

    private String name;

    private FiledType type;

    private String comment;

    private Integer primaryKey = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCfName() {
        return CodeStyle.ofUlCode(name).toStyle(CodeStyle.NamedStyle.CAMEL).toStyle(CodeStyle.NamedStyle.CAP_FIRST).toString();
    }

    public FiledType getType() {
        return type;
    }

    public void setType(FiledType type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Integer primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
        return "TableField{" +
                "name=" + name +
                ",type=" + type +
                ",comment=" + comment +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableField that = (TableField) o;
        return Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, comment);
    }
}

