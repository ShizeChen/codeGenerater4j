package com.codegenerater.util;


import com.codegenerater.container.GlobalContext;
import com.codegenerater.container.GlobalContextAware;
import com.codegenerater.container.ManagedBean;

/**
 * @author created by XD.Wang
 * Date 2020/9/7.
 */
@ManagedBean
final public class GlobalContextHolder implements GlobalContextAware {

    public static GlobalContext globalContext;

    @Override
    public void setGlobalContext(GlobalContext c) {
        globalContext = c;
    }

}
