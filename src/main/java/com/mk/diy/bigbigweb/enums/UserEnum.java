package com.mk.diy.bigbigweb.enums;

/**
 * @author wanghao
 * @create 2017-10-15 19:48
 */
public enum UserEnum {
    UNBIND("UNBIND","未绑定"),
    BIND("BIND","已绑定");
    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    UserEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
