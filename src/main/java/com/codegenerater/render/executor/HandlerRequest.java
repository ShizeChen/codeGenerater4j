package com.codegenerater.render.executor;

import com.dlin.model.Model;

/**
 * 节点执行上下文
 *
 * @author XD.Wang
 * Date 2020/7/25.
 */
public class HandlerRequest {

    private Model model;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
