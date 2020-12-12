package com.codegenerater.render.executor;

/**
 * 责任链执行异常
 *
 * @author XD.Wang
 * Date 2020/7/25.
 */
public class ChainExecFailedException extends RuntimeException {

    private static final String DEFAULT_MSG = "chain execute failed";

    private static final String DEFAULT_CODE = "FAILED";

    /**
     * 异常描述
     */
    private String msg;
    /**
     * 异常码
     */
    private String code;
    /**
     * 附带信息
     */
    private Object data;

    public ChainExecFailedException(String msg, String code, Object data) {
        super(msg);
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static ChainExecFailedException err() {
        return new ChainExecFailedException(DEFAULT_MSG, DEFAULT_CODE, null);
    }

    public static ChainExecFailedException err(String msg, String code) {
        return new ChainExecFailedException(msg, code, null);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
