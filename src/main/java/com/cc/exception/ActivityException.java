package com.cc.exception;

/**
 * Created by xn032607 on 2017/2/9.
 */
public class ActivityException extends RuntimeException {
    private String code;
    private String desc;

    public ActivityException( String desc) {
        super(desc);
        this.desc = desc;
    }

    public ActivityException(String code, String desc) {
        super(desc);
        this.code = code;
        this.desc = desc;
    }

    public ActivityException(String message, Throwable cause, String code, String desc) {
        super(message, cause);
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
