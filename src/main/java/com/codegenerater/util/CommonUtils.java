package com.codegenerater.util;

import java.util.*;
import java.util.function.Function;

/**
 * 简单工具
 *
 * @author XD.Wang
 * Date 2017/3/31.
 */
final public class CommonUtils {

    private CommonUtils() {
    }

    public static <K, V> Map<K, List<V>> multiGroupBy(List<V> list, Function<V, K> func) {
        if (list != null && list.size() > 0) {
            Map<K, List<V>> map = new HashMap<>(list.size());
            for (V value : list) {
                K key = func.apply(value);
                List<V> currentValues = map.computeIfAbsent(key, k -> new ArrayList<>());
                currentValues.add(value);
            }
            return map;
        }
        return Collections.emptyMap();
    }

    public static <K, V> Map<K, V> singleGroupBy(Collection<V> list, Function<V, K> func) {
        if (list != null && list.size() > 0) {
            Map<K, V> map = new HashMap<>(list.size());
            for (V value : list) {
                map.put(func.apply(value), value);
            }
            return map;
        }
        return Collections.emptyMap();
    }

    public static <V> V getOne(Collection<V> c) {
        if (c == null || c.size() == 0) {
            return null;
        }
        return c.stream().findFirst().orElse(null);
    }

}
