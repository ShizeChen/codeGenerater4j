package com.codegenerater.render.executor;


import com.codegenerater.render.Render;

/**
 * 渲染节点基类
 *
 * @author XD.Wang
 * Date 2020/7/25.
 */
public abstract class RenderWrapper {

    /**
     * 下游渲染
     */
    protected RenderWrapper nextHandler;

    /**
     * 执行渲染操作的渲染器
     */
    protected Render render;

    public RenderWrapper(Render render) {
        this.render = render;
    }

    /**
     * 判断是否有下游渲染
     *
     * @return 是否有下游渲染
     */
    protected boolean hasNext() {
        return nextHandler != null;
    }

    /**
     * 设定本渲染的下游渲染
     *
     * @param nextHandler 下游渲染
     * @return 下游渲染
     */
    public RenderWrapper nextIs(RenderWrapper nextHandler) {
        this.nextHandler = nextHandler;
        return this.nextHandler;
    }

    /**
     * 调用渲染器的渲染逻辑
     *
     * @param req  渲染逻辑必要上下文
     * @param resp 执行结果
     * @return 是否执行成功
     */
    protected abstract boolean render(HandlerRequest req, HandlerResponse resp);

    /**
     * 渲染前触发
     *
     * @param req 业务逻辑入参
     */
    protected abstract void actionBeforeRend(HandlerRequest req);

    /**
     * 渲染后触发
     *
     * @param req 业务逻辑入参
     */
    protected abstract void actionAfterRend(HandlerRequest req);

}
