package com.codegenerater.util;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static com.codegenerater.util.CodeStyle.NamedStyle.*;

/**
 * @description:
 * @author: chenshize02
 * @create: 2020-12-12 16:31
 **/
public class CodeStyle {

    private final String source;

    private final NamedStyle style;

    public enum NamedStyle {
        /**
         * 驼峰风格
         */
        CAMEL,
        /**
         * 下划线风格
         */
        UNDERLINE,
        /**
         * 首字母大写的驼峰风格
         */
        CAP_FIRST
    }

    public CodeStyle(String source, NamedStyle style) {
        this.source = source;
        this.style = style;
    }

    public static CodeStyle ofCamelCode(String source) {
        return new CodeStyle(Optional.ofNullable(source).orElse(""), CAMEL);
    }

    public static CodeStyle ofUlCode(String source) {
        return new CodeStyle(Optional.ofNullable(source).orElse(""), UNDERLINE);
    }

    public static CodeStyle ofCapCode(String source) {
        return new CodeStyle(Optional.ofNullable(source).orElse(""), CAP_FIRST);
    }

    private static final Map<EnumListLocateWrapper, Function<String, String>> CONVERT_FUNCTION_SELECTOR = Maps.newHashMap();

    static {
        CONVERT_FUNCTION_SELECTOR.put(new EnumListLocateWrapper(CAMEL, UNDERLINE), StrUtil::toUnderlineCase);
        CONVERT_FUNCTION_SELECTOR.put(new EnumListLocateWrapper(CAMEL, CAP_FIRST), StrUtil::upperFirst);
        CONVERT_FUNCTION_SELECTOR.put(new EnumListLocateWrapper(UNDERLINE, CAMEL), StrUtil::toCamelCase);
        CONVERT_FUNCTION_SELECTOR.put(new EnumListLocateWrapper(UNDERLINE, CAP_FIRST), StrUtil::upperFirst);
        CONVERT_FUNCTION_SELECTOR.put(new EnumListLocateWrapper(CAP_FIRST, UNDERLINE), s -> StrUtil.toUnderlineCase(StrUtil.lowerFirst(s)));
        CONVERT_FUNCTION_SELECTOR.put(new EnumListLocateWrapper(CAP_FIRST, CAMEL), StrUtil::lowerFirst);
    }

    /**
     * 基本规则：
     * 1 下划线 -> 驼峰
     * 2 驼峰 -> 下划线
     * 3 驼峰 -> 首字母大写的驼峰
     *
     * @param targetStyle 目标风格
     * @return 目标代码
     */
    public CodeStyle toStyle(NamedStyle targetStyle) {
        if (targetStyle == style) return this;
        String s = CONVERT_FUNCTION_SELECTOR.get(new EnumListLocateWrapper(style, targetStyle)).apply(source);
        return new CodeStyle(s, targetStyle);
    }

    @Override
    public String toString() {
        return source;
    }



}
