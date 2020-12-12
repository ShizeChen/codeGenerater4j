package com.codegenerater.util;

import cn.hutool.core.util.StrUtil;

import java.util.function.Function;
import java.util.stream.Stream;

public class EnumUtils {

    /**
     * 根据外部值进行枚举查询
     *
     * @param val       外部值
     * @param extractor 枚举值提取器
     * @return 外部值对应的目标枚举
     */
    @SafeVarargs
    public static <E extends Enum<?>> E getByValOrThrow(String val, Function<E, String> extractor, E... enums) {
        return Stream.of(enums).filter(t -> StrUtil.equalsAnyIgnoreCase(val, extractor.apply(t))).findAny().orElse(null);
    }

}
