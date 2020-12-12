package com.codegenerater.templates.spec;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记模板配置类
 *
 * @author wuhuilin
 * Date 2020-8-23.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Templates {

    boolean skip() default false;

}
