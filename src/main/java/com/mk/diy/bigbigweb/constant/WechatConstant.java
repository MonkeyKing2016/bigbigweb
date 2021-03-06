package com.mk.diy.bigbigweb.constant;

/**
 * 微信常量类
 *
 * @author wanghao
 * @create 2017-10-14 16:02
 */
public class WechatConstant {
    public static final String AppId = "wxbc39091dcba6bf22";
    public static final String Appsecret = "99b15def1fb9ee752765e2869c08a14f";
    public static final String Token = "b8ed1ac5d6a52839489774e2676e3e27";
    public static final String EncodingAESKey = "3Db9zs7j5Lz4uEY51k8gFB9kWBKeErEJYc8SaVA1bCJ";

    public static String accessToken = "";
    /**
     * 返回消息类型：图片 1
     */
    public static final String RESP_MSG_TYPE_IMAGE = "image";

    /**
     * 返回消息类型：音乐 2
     */
    public static final String RESP_MSG_TYPE_MUSIC = "music";

    /**
     * 返回消息类型：语音 3
     */
    public static final String RESP_MSG_TYPE_VOICE = "voice";

    /**
     * 返回消息类型：视频 4
     */
    public static final String RESP_MSG_TYPE_VIDEO = "video";

    /**
     * 返回消息类型：图文 5
     */
    public static final String RESP_MSG_TYPE_NEWS = "news";

    /**
     * 返回消息类型：文本 6
     */
    public static final String RESP_MSG_TYPE_TEXT = "text";

    /**
     * 请求消息类型：文本 1
     */
    public static final String REQ_MSG_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片 2
     */
    public static final String REQ_MSG_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接 3
     */
    public static final String REQ_MSG_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置 4
     */
    public static final String REQ_MSG_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：语音消息 5
     */
    public static final String REQ_MSG_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：视频消息 6
     */
    public static final String REQ_MSG_TYPE_VIDEO = "video";

    /**
     * 请求消息类型：小视频消息 7
     */
    public static final String REQ_MSG_TYPE_SHORTVIDEO = "shortvideo";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MSG_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：用户已关注时的事件推送
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";

    /**
     * 事件类型：用户上报地理位置
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 事件类型：VIEW(自定义菜单跳转链接时的事件)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";


    public static final String CONSTANT_MSG_TYPE = "MsgType";

    public static final String CONSTANT_EVENT = "event";
}
