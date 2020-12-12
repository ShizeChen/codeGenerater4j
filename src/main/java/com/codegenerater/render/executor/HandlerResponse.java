package com.codegenerater.render.executor;


import com.codegenerater.render.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点执行结果收集
 *
 * @author XD.Wang
 * Date 2020/7/25.
 */
public class HandlerResponse {

    /**
     * 是否执行成功
     */
    private boolean success;

    /**
     * 解析出来的视图
     */
    private final List<View> views = new ArrayList<>();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<View> getViews() {
        return views;
    }

    public void addViews(List<View> views) {
        this.views.addAll(views);
    }

    public void addView(View views) {
        this.views.add(views);
    }

}
