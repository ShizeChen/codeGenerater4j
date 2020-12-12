package com.codegenerater.group.spec;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记模板组
 *
 * @author wuhuilin
 * Date 2020-9-14.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Group {
    /**
     * 模板组ID，必填
     *
     * @return 模板组ID
     */
    String value();
}
