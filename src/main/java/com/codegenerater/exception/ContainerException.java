package com.codegenerater.exception;

/**
 * @description: 容器异常
 * @author: chenshize02
 * @create: 2020-12-12 16:55
 **/
public class ContainerException extends RuntimeException{
    public ContainerException(String message) {
        super(message);
    }

    public ContainerException(String message, Throwable cause) {
        super(message, cause);
    }
}
