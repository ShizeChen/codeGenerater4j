package com.codegenerater.model;

import com.codegenerater.util.CodeStyle;
import com.codegenerater.util.CommonUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

import static com.codegenerater.model.enums.TableModelBuiltInAttr.*;
import static com.codegenerater.util.CodeStyle.ofUlCode;

/**
 * @description: 表属性
 * @author: chenshize02
 * @create: 2020-12-12 16:21
 **/
public class TableModel extends Model {

    private String desc;

    private String bizPrefix;

    private final Map<String, TableField> fields = new HashMap<>();

    private final Set<String> primaryKeys = Sets.newHashSet();

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBizPrefix() {
        return bizPrefix;
    }

    public void setBizPrefix(String bizPrefix) {
        this.bizPrefix = bizPrefix;
    }

    public void addTableFiled(TableField field) {
        fields.put(field.getName(), field);
    }

    public Set<TableField> getFields() {
        return new HashSet<>(fields.values());
    }

    public void addTablePrimaryKey(String field) {
        primaryKeys.add(field);
    }

    public Set<String> getPrimaryKey() {
        return primaryKeys;
    }

    @Override
    public String toString() {
        return Joiner.on("\n").join(fields.values().stream().map(TableField::toString).collect(Collectors.toList()));
    }

    @Override
    public Map<String, Object> extractAttrs() {
        // extract table attrs
        addBuiltInAttr(MODEL.key(), ofUlCode(name).toStyle(CodeStyle.NamedStyle.CAMEL).toString())
                .addBuiltInAttr(MODEL_DESC.key(), Optional.ofNullable(desc).orElse(""))
                .addBuiltInAttr(MODEL_PREFIX.key(), Optional.ofNullable(bizPrefix).orElse(""))
                .addBuiltInAttr(MODEL_CAPTAl_FIRST_NAME.key(), ofUlCode(name).toStyle(CodeStyle.NamedStyle.CAMEL).toStyle(CodeStyle.NamedStyle.CAP_FIRST).toString())
                .addBuiltInAttr(MODEL_CAMEL_NAME.key(), ofUlCode(name).toStyle(CodeStyle.NamedStyle.CAMEL).toString())
                .addBuiltInAttr(MODEL_UNDERLINE_NAME.key(), ofUlCode(name).toString())
                // extract table filed attrs
                .addBuiltInAttr(FIELD.key(), Objects.requireNonNull(fields.values()).stream().map(this::tableFiledToMap).collect(Collectors.toList()));
        // extract single primary key
        String singleKey = CommonUtils.getOne(this.primaryKeys);
        addBuiltInAttr(SINGLE_PRIMARY_KEY.key(), tableFiledToMap(fields.get(singleKey)));
        return super.extractAttrs();
    }

    private Map<String, Object> tableFiledToMap(TableField f) {
        Map<String, Object> fieldsAttrs = Maps.newHashMap();
        fieldsAttrs.put(FIELD_CAMEL_NAME.key(), ofUlCode(f.getName()).toStyle(CodeStyle.NamedStyle.CAMEL).toString());
        fieldsAttrs.put(FIELD_CAPTAl_FIRST_NAME.key(), f.getCfName());
        fieldsAttrs.put(FIELD_DESC.key(), Optional.ofNullable(f.getComment()).orElse(""));
        fieldsAttrs.put(FIELD_J_TYPE.key(), f.getType().getJavaType());
        fieldsAttrs.put(FIELD_J_STANDARD_TYPE.key(), f.getType().getStandardJavaType());
        fieldsAttrs.put(FIELD_JDBC_TYPE.key(), f.getType().getJdbcType());
        fieldsAttrs.put(FIELD_UNDERLINE_NAME.key(), ofUlCode(f.getName()).toString());
        return fieldsAttrs;
    }

}