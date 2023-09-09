package com.ranchuanyin.schoolcat.enums;


public enum SchoolCatExceptionEnum {

    USER_NOT_LOGGED_IN("用户未登录"),
    PASSWORD_ERROR("用户名或密码错误");

    private String desc;

    SchoolCatExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "SchoolCatExceptionEnum{" +
                "desc='" + desc + '\'' +
                "} " + super.toString();
    }
}
