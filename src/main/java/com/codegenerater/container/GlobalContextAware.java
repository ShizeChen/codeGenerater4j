package com.codegenerater.container;

/**
 * 为组件提供全局配置
 *
 * @author created by XD.Wang
 * Date 2020/7/12.
 */
public interface GlobalContextAware {

    void setGlobalContext(GlobalContext globalContext);

}
