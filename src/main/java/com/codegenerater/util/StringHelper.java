package com.codegenerater.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: chenshize02
 * @create: 2020-12-11 17:12
 **/
public class StringHelper {
    private StringHelper() {
    }

    public static final String STR_SPLIT_DOT = ",";
    private static final Pattern HUMP_PATTERN = Pattern.compile("[A-Z]");
    private static final Pattern LINE_PATTERN = Pattern.compile("_(\\w)");


    /**
     * 将以,分割的字符串转换成int list
     *
     * @param str 字符串
     * @return List<Integer>
     */
    public static List<Integer> getIntegerListSplitByDot(String str) {
        if (null == str) {
            return new ArrayList<>();
        }
        String[] strings = str.split(STR_SPLIT_DOT);
        try {
            return Arrays.stream(strings).map(Integer::parseInt).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 将以,分割的字符串转换成Long list
     *
     * @param str 字符串
     * @return List<Integer>
     */
    public static List<Long> getLongListSplitByDot(String str) {
        if (null == str) {
            return new ArrayList<>();
        }
        String[] strings = str.split(STR_SPLIT_DOT);
        try {
            return Arrays.stream(strings).map(Long::parseLong).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 将以,分割的字符串转换成String list
     *
     * @param str 字符串
     * @return List<Integer>
     */
    public static List<String> getStringListSplitByDot(String str) {
        if (null == str) {
            return new ArrayList<>();
        }
        String[] strings = str.split(STR_SPLIT_DOT);
        try {
            return Arrays.asList(strings);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * 将字符串的首字母小写
     *
     * @param str 字符串
     * @return 转换后的字符串
     */
    public static String lowerFirstChar(String str) {
        if (null == str) {
            return null;
        }
        return str.substring(0, 1).toLowerCase() + str.substring(1);

    }

    /**
     * 将字符串的首字母大写
     *
     * @param str 字符串
     * @return 转换后的字符串
     */
    public static String upperFirstChar(String str) {
        if (null == str) {
            return null;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


    /**
     * 驼峰转下划线
     *
     * @param str 驼峰字符串
     * @return 下划线字符串
     */
    public static String humpToLine(String str) {
        if (!HUMP_PATTERN.matcher(str).find()) {
            return str;
        }
        Matcher matcher = HUMP_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    /**
     * 下划线转驼峰
     *
     * @param str 下划线字符串
     * @return 驼峰字符串
     */
    public static String lineToHump(String str) {
        if (!LINE_PATTERN.matcher(str).find()) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = LINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
