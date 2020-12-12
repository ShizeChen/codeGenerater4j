package com.codegenerater.container;

import cn.hutool.core.util.ReflectUtil;

public class ManagedBeanSpec {

    private Object instant;

    private Class<?> clazz;

    private String id;

    private ManagedBeanSpec(Object instant, Class<?> clazz, String id) {
        this.instant = instant;
        this.clazz = clazz;
        this.id = id;
    }

    public static <T> ManagedBeanSpec of(Class<T> clazz) {
        T inst = ReflectUtil.newInstance(clazz);
        return new ManagedBeanSpec(inst, clazz, clazz.getSimpleName());
    }

    public Object getInstant() {
        return instant;
    }

    public void setInstant(Object instant) {
        this.instant = instant;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
