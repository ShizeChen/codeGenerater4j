package com.codegenerater.util;

import cn.hutool.log.StaticLog;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author created by XD.Wang
 * Date 2020/9/14.
 */
final public class ExceptionUtils {

    /**
     * 支持抛出IOException的Consumer
     *
     * @param <T> 目标类型
     */
    @FunctionalInterface
    public interface IOExceptionConsumer<T> {

        /**
         * Performs this operation on the given argument.
         *
         * @param t the input argument
         */
        void accept(T t) throws IOException;

    }

    public static <T> Consumer<T> ioConsumer(IOExceptionConsumer<T> consumer) {
        return param -> {
            try {
                consumer.accept(param);
            } catch (IOException e) {
                StaticLog.error(e, "io exception occurred!");
            }
        };
    }

}
