package com.cc.exception;

/**
 * Created by xn032607 on 2017/2/24.
 */
public class BizException extends RuntimeException {
    String msg;

    public BizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public BizException(String message, Throwable cause, String msg) {
        super(message, cause);
        this.msg = msg;
    }

    public BizException(String message, Throwable e) {
        super(message,e);
    }
}
