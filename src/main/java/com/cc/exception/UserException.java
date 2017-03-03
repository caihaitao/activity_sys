package com.cc.exception;

/**
 * Created by xn032607 on 2017/2/17.
 */
public class UserException extends RuntimeException {
    private String code;
    private String desc;

    public UserException() {
    }

    public UserException( String desc) {
        super(desc);
        this.desc = desc;
    }

    public UserException(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public UserException(String message, String code, String desc) {
        super(message);
        this.code = code;
        this.desc = desc;
    }

    public UserException(String message, Throwable cause, String code, String desc) {
        super(message, cause);
        this.code = code;
        this.desc = desc;
    }

}
