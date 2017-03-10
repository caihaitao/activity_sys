package com.cc.activity.bean;

import com.cc.base.BaseObject;

/**
 * Created by xn032607 on 2017/3/10.
 */
public class ActivityTypeDTO extends BaseObject {

    private Integer type;

    private String typeName;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public ActivityTypeDTO(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public ActivityTypeDTO() {
    }
}
