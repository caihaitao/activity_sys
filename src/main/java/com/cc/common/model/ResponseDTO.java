package com.cc.common.model;

import com.cc.base.BaseObject;

/**
 * Created by xn032607 on 2017/2/20.
 */
public class ResponseDTO extends BaseObject {
    private boolean flag;
    private String errMsg;

    public ResponseDTO() {
    }

    public ResponseDTO(boolean flag, String errMsg) {
        this.flag = flag;
        this.errMsg = errMsg;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
