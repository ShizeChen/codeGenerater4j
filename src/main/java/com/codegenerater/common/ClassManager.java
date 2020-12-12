package com.codegenerater.common;

import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author created by XD.Wang
 * Date 2020/8/28.
 */
public class ClassManager {

    private static final Set<Class<?>> CLASSES = Sets.newHashSet();

    private static final ClassPathScanHandler SCAN_HANDLER = new ClassPathScanHandler();

    static {
        CLASSES.addAll(getClazzByPath("com.dlin"));
    }

    public static Set<Class<?>> getCoderPlusClazz() {
        return CLASSES;
    }

    public static Set<Class<?>> getClazzByPath(String... path) {
        return Arrays.stream(path).filter(Objects::nonNull).map(p -> new ArrayList<>(SCAN_HANDLER.getPackageAllClasses(p, true)))
                .flatMap(Collection::stream).collect(Collectors.toSet());
    }

}
