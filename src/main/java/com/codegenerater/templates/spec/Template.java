package com.codegenerater.templates.spec;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记模板
 *
 * @author wuhuilin
 * Date 2020-8-23.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Template {

    /**
     * 模板的ID，必填
     * @return 模板的ID
     */
    String value();

}
