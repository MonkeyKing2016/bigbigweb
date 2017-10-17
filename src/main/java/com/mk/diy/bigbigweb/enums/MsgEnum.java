package com.mk.diy.bigbigweb.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * @create 2017-10-15 19:48
 */
public enum MsgEnum {
    TEXT("text","文本"),
    IMAGE("image","图片"),
    LINK("link","链接"),
    LOCATION("location","地址"),
    VOICE("voice","声音"),
    VIDEO("video","视频"),
    SHORTVIDEO("shortvideo","小视频"),
    subscribe("subscribe","关注"),
    unsubscribe("unsubscribe","取消关注"),
    SCAN("SCAN","事件推送"),
    LOCATION_EVENT("LOCATION","上报地理位置"),
    CLICK("CLICK","菜单点击"),
    VIEW("VIEW","菜单显示");
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

    MsgEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    private static Map<String,String> map = new HashMap<>();

    static {
        for (MsgEnum msgEnum : MsgEnum.values()) {
            map.put(msgEnum.getCode(),msgEnum.getValue());
        }
    }
    public static String getCode (String key) {
        return map.get(key);
    }
}
