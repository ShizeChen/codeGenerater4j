package com.codegenerater.util;

import java.util.*;

/**
 * @description: List比较功能增强
 *  将EnumList的equal操作委托给EnumListLocateWrapper执行
 * @author: XD.Wang
 * @create: 2020-12-12 16:33
 **/
public class EnumListLocateWrapper {private final List<Enum<?>> enumList;

    public EnumListLocateWrapper(Enum<?>... enumList) {
        this.enumList = Arrays.asList(enumList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EnumListLocateWrapper that = (EnumListLocateWrapper) o;
        return isEqualList(enumList, that.enumList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enumList.toArray());
    }

    private static boolean isEqualList(Collection<?> list1, Collection<?> list2) {
        if (list1 == list2) {
            return true;
        } else if (list1 != null && list2 != null && list1.size() == list2.size()) {
            Iterator<?> it1 = list1.iterator();
            Iterator<?> it2 = list2.iterator();
            Object obj1;
            Object obj2;
            while(true) {
                if (it1.hasNext() && it2.hasNext()) {
                    obj1 = it1.next();
                    obj2 = it2.next();
                    if (obj1 == null) {
                        if (obj2 == null) {
                            continue;
                        }
                    } else if (obj1.equals(obj2)) {
                        continue;
                    }
                    return false;
                }
                return !it1.hasNext() && !it2.hasNext();
            }
        } else {
            return false;
        }
    }
}
