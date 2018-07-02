package org.banyan.spring.boot.starter.dubbo;

/**
 * Copyright (C), 2017, Banyan Network Foundation
 *
 * @author levi
 * @version 0.0.1
 * @desc duubo异常
 * @date 2018-01-16 14:15:42
 */
public class DubboSystemException extends RuntimeException {

    public DubboSystemException() {
        super();
    }

    public DubboSystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DubboSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public DubboSystemException(String message) {
        super(message);
    }

    public DubboSystemException(Throwable cause) {
        super(cause);
    }
}
