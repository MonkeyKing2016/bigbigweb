package com.mk.diy.bigbigweb.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信常量类
 *
 * @author wanghao
 * @create 2017-10-14 16:02
 */
@Component
public class WechatConstant {

    @Value("${wechat.AppId}")
    private String appId;
    @Value("${wechat.AppSecret}")
    private String appSecret;
    @Value("${wechat.Token}")
    private String token;
    @Value("${wechat.EncodingAESKey}")
    private String encodingAESKey;

    public static String AppId;
    public static String AppSecret;
    public static String Token;
    public static String EncodingAESKey;

    public static Map<String,String> Map = new HashMap<>();

    @PostConstruct
    private void init(){
        AppId = this.appId;
        AppSecret = this.appSecret;
        Token = this.token;
        EncodingAESKey = this.encodingAESKey;
        Map.put("15346622051",null);
        Map.put("15346622052",null);
        Map.put("15346622053",null);
        Map.put("15346622054",null);
        Map.put("15346622055",null);
        Map.put("15346622056",null);
    }
    // 2017-10-20 15:15:31.424
    public static String AccessToken = "Km3XpJXdymQ5mjTqnXvvrIT1FVnluxPJJUHXoOYtsc7J67BjZPadXl91lEBYjJAtSFQKQlVrpBWxGqgi5eHQi83sVsLwNJQtF63-T21-3nPA6t3mE-TiCHE27swzAF1UWHOfABAMXE";

    public static final String TOKEN_TYPE = "client_credential";

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
     * 事件类型：用户菜单上报地理位置信息
     */
    public static final String EVENT_TYPE_LOCATION_SELECT = "location_select";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 事件类型：VIEW(自定义菜单跳转链接时的事件)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";


    public static final String CONSTANT_MSG_TYPE = "MsgType";

    public static final String CONSTANT_EVENT = "Event";
}
