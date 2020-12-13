package com.codegenerater.common;

import com.codegenerater.util.EnumUtils;
import com.google.common.base.Joiner;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: Mysql和JDBC和HQL类型的对应关系
 * @author: chenshize02
 * @create: 2020-12-12 16:24
 **/
public enum FiledType {

    NUM_TINYINT(Integer.class, "TINYINT", "TINYINT", "TINYINT"),
    NUM_SMALLINT(Integer.class, "SMALLINT", "SMALLINT", "SMALLINT"),
    NUM_MEDIUMINT(Integer.class, "MEDIUMINT", "INTEGER", "INTEGER"),
    NUM_INT(Integer.class, "INT", "INT", "INTEGER"),
    NUM_BIGINT(Long.class, "BIGINT", "BIGINT", "BIGINT"),
    NUM_FLOAT(Float.class, "FLOAT", "FLOAT", "FLOAT"),
    NUM_DOUBLE(Double.class, "DOUBLE", "DOUBLE", "DOUBLE"),
    NUM_DECIMAL(BigDecimal .class, "DECIMAL", "DECIMAL", "DECIMAL"),
    TIME_DATE(Date .class, "DATE", "DATE", "DATE"),
    TIME_DATETIME(Date.class, "DATETIME", "DATETIME", "TIMESTAMP"),
    TIME_TIMESTAMP(Date.class, "TIMESTAMP", "TIMESTAMP","TIMESTAMP"),
    TIME_TIME(Date.class, "TIME", "TIME", "TIME"),
    STR_CHAR(String.class, "CHAR", "CHAR", "CHAR"),
    STR_VARCHAR(String.class, "VARCHAR", "VARCHAR", "VARCHAR"),
    STR_TEXT(String.class, "TEXT", "VARCHAR", "VARCHAR"),
    LONG_TEXT(String.class, "LONGTEXT", "VARCHAR", "VARCHAR"),
    BYTE_BLOB(Byte[].class, "BLOB", "BOOLEAN", "BLOB"),
    STRING(String.class, "VARCHAR", "STRING", "VARCHAR");

    private final Class<?> javaType;

    private final String sqlType;

    private final String hqlType;

    private final String jdbcType;



    FiledType(Class<?> javaType, String sqlType, String hqlType, String jdbcType) {
        this.javaType = javaType;
        this.sqlType = sqlType;
        this.hqlType = hqlType;
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return javaType.getSimpleName();
    }

    public String getStandardJavaType() {
        return javaType.getName();
    }

    public String getSqlType() {
        return sqlType;
    }

    public String getHqlType() {
        return hqlType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public static FiledType getByJdbcType(String type) {
        return EnumUtils.getByValOrThrow(type, FiledType::getJdbcType, FiledType.values());
    }

    public static FiledType getBySqlType(String type) {
        return EnumUtils.getByValOrThrow(type, FiledType::getSqlType, FiledType.values());
    }

    public static FiledType getByHqlType(String type) {
        return EnumUtils.getByValOrThrow(type, FiledType::getHqlType, FiledType.values());
    }

    public static FiledType getByJavaType(String type) {
        return EnumUtils.getByValOrThrow(type, FiledType::getJavaType, FiledType.values());
    }

    public static String getMySqlDDLDefaultVale(FiledType filedType) {
        String defaultValue = null;
        switch (filedType) {
            case NUM_TINYINT:
            case NUM_SMALLINT:
            case NUM_MEDIUMINT:
            case NUM_INT:
            case NUM_BIGINT:
                defaultValue = "0";
                break;
            case NUM_FLOAT:
            case NUM_DOUBLE:
            case NUM_DECIMAL:
                defaultValue = "0.00";
                break;
            case TIME_DATE:
            case TIME_DATETIME:
            case TIME_TIMESTAMP:
            case TIME_TIME:
            case STR_CHAR:
            case STR_VARCHAR:
            case STR_TEXT:
            case LONG_TEXT:
            case STRING:
                defaultValue = "";
                break;
            case BYTE_BLOB:
                defaultValue = "false";
                break;
        }
        return defaultValue;
    }
    @Override
    public String toString() {
        return Joiner.on("|").join(javaType, jdbcType, sqlType);
    }

}
