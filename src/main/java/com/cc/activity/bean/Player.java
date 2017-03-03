package com.cc.activity.bean;


import com.cc.base.BaseObject;

public class Player extends BaseObject {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String pname;

    private String mobile;

    private String imagePath;

    public Player() {
    }

    public Player(String pname, String mobile, String imagePath) {
        this.pname = pname;
        this.mobile = mobile;
        this.imagePath = imagePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath == null ? null : imagePath.trim();
    }
}