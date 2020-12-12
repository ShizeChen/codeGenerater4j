package com.codegenerater.render.executor;

import cn.hutool.log.StaticLog;

/**
 * 可配置的连续工具，用来组合多个渲染器
 *
 * @author XD.Wang
 * Date 2020/7/25.
 */
public class ConfigurableRendersExecutor {

    public enum FailedHandleStrategy {
        /**
         * 终止并抛出异常
         */
        THROW_EXCEPTION,
        /**
         * 忽略并继续进行
         */
        IGNORE,
        /**
         * 终止并跳出
         */
        BREAK
    }

    /**
     * 节点执行失败后的动作
     */
    private final FailedHandleStrategy failedHandleStrategy;

    /**
     * 责任链根节点
     */
    private final RenderWrapper headHandler;

    /**
     * 当前执行的责任链节点
     */
    private RenderWrapper curHandler;

    public ConfigurableRendersExecutor(RenderWrapper headHandler) {
        this.headHandler = headHandler;
        this.curHandler = headHandler;
        this.failedHandleStrategy = FailedHandleStrategy.THROW_EXCEPTION;
    }

    public ConfigurableRendersExecutor(RenderWrapper headHandler, FailedHandleStrategy failedHandleStrategy) {
        this.headHandler = headHandler;
        this.curHandler = headHandler;
        this.failedHandleStrategy = failedHandleStrategy;
    }

    /**
     * 执行责任链
     *
     * @param req  节点逻辑必要上下文
     * @param resp 执行结果
     */
    public void execute(HandlerRequest req, HandlerResponse resp) {
        boolean proceed = true;
        while (proceed) {
            proceed = render(req, resp);
        }
    }

    /**
     * 执行节点逻辑 模板逻辑
     *
     * @param req  节点逻辑必要上下文
     * @param resp 执行结果
     * @return 是否继续执行
     */
    private boolean render(HandlerRequest req, HandlerResponse resp) {
        boolean isSuccess = false;
        try {
            curHandler.actionBeforeRend(req);
            isSuccess = curHandler.render(req, resp);
            curHandler.actionAfterRend(req);
        } catch (ChainExecFailedException e) {
            StaticLog.warn("render node execute failed：" + e.getMessage());
            if (failedHandleStrategy == FailedHandleStrategy.THROW_EXCEPTION) {
                throw e;
            }
        }
        boolean proceed = (isSuccess || failedHandleStrategy == FailedHandleStrategy.IGNORE) && curHandler.hasNext();
        if (proceed) {
            curHandler = curHandler.nextHandler;
        }
        return proceed;
    }

}
