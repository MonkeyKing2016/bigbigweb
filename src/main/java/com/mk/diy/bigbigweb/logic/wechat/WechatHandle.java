package com.mk.diy.bigbigweb.logic.wechat;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatApiConstant;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.model.request.*;
import com.mk.diy.bigbigweb.service.impl.WechatServiceImpl;
import com.mk.diy.bigbigweb.utils.AesException;
import com.mk.diy.bigbigweb.utils.HttpsUtil;
import com.mk.diy.bigbigweb.utils.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 微信处理类
 *
 * @author wanghao
 * @create 2017-10-15 11:55
 */
@Repository
public class WechatHandle {
    Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    private MsgHandle msgHandle;

    public String processRequest(String xmlText) throws AesException {
        String result = "SUCCESS";
        // 解析 xml 获得所有数据map
        Map<String, String> map = XMLParse.analysis(xmlText);
        // 转换成json 并转换成对象
        result = switchMsg(map);
        return result;
    }

    private String switchMsg(Map<String, String> map) throws AesException {
        String result = "SUCCESS";
        switch (map.get(WechatConstant.CONSTANT_MSG_TYPE)) {
            case WechatConstant.REQ_MSG_TYPE_TEXT:
                // 获得对象
                TextMsg textMsg = JSON.parseObject(JSON.toJSONString(map), TextMsg.class);
                // 处理对象
                result = msgHandle.process(textMsg);
                break;
            case WechatConstant.REQ_MSG_TYPE_IMAGE:
                result = msgHandle.process(JSON.parseObject(JSON.toJSONString(map), ImageMsg.class));
                break;
            case WechatConstant.REQ_MSG_TYPE_LINK:
                result = msgHandle.process(JSON.parseObject(JSON.toJSONString(map), LinkMsg.class));
                break;
            case WechatConstant.REQ_MSG_TYPE_LOCATION:
                result = msgHandle.process(JSON.parseObject(JSON.toJSONString(map), LocationMsg.class));
                break;
            case WechatConstant.REQ_MSG_TYPE_VOICE:
                result = msgHandle.process(JSON.parseObject(JSON.toJSONString(map), VoiceMsg.class));
                break;
            case WechatConstant.REQ_MSG_TYPE_VIDEO:
                result = msgHandle.process(JSON.parseObject(JSON.toJSONString(map), VideoMsg.class));
                break;
            case WechatConstant.REQ_MSG_TYPE_SHORTVIDEO:
                result = msgHandle.process(JSON.parseObject(JSON.toJSONString(map), VideoMsg.class));
                break;
            case WechatConstant.REQ_MSG_TYPE_EVENT:
                String event = map.get(WechatConstant.CONSTANT_EVENT);
                result = switchEvent(map, event);
                break;
            default:
                throw new AesException(AesException.UnkownMsg);
        }
        return result;
    }

    private String switchEvent(Map<String, String> map, String event) throws AesException {
        String result = "SUCCESS";
        switch (event) {
            case WechatConstant.EVENT_TYPE_SUBSCRIBE:
                result = msgHandle.processSubscribe(JSON.parseObject(JSON.toJSONString(map), SubscribeEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_UNSUBSCRIBE:
                result = msgHandle.processUnsubsribe(JSON.parseObject(JSON.toJSONString(map), SubscribeEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_SCAN:
                result = msgHandle.processScan(JSON.parseObject(JSON.toJSONString(map), SubscribeEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_LOCATION:
                result = msgHandle.process(JSON.parseObject(JSON.toJSONString(map), LocationEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_CLICK:
                result = msgHandle.processClick(JSON.parseObject(JSON.toJSONString(map), MenuEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_VIEW:
                result = msgHandle.processView(JSON.parseObject(JSON.toJSONString(map), MenuEvent.class));
                break;
            default:
                throw new AesException(AesException.UnkownEvent);
        }
        return result;
    }

    public boolean getToken() {
        boolean isOk = false;
        try {
            String url = String.format(WechatApiConstant.GET_TOKEN, WechatConstant.TOKEN_TYPE, WechatConstant.AppId, WechatConstant.AppSecret);
            String result = HttpsUtil.get(url, null,HttpsUtil.URL_PARAM_DECODECHARSET_UTF8);

            Map map = JSON.parseObject(result, Map.class);
            Object token = map.get("access_token");

            if (token != null) {
                WechatConstant.AccessToken = token.toString();
                logger.info(String.format("AccessToken:%s",WechatConstant.AccessToken));
                isOk = true;
            } else {
                logger.info(String.format("获取 AccessToken 失败。errorCode:%s.errorMsg:%s", map.get("errcode"), map.get("errmsg")));
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return isOk;
    }

    public boolean sendMsg(CustomSendMsg sendMsg) {
        boolean isOk = false;
        try {
            String url = String.format(WechatApiConstant.CUSTOM_SEND_POST, WechatConstant.AccessToken);

            JSON.toJSONString(sendMsg);
            // todo
            String result = HttpsUtil.post(url, null , HttpsUtil.URL_PARAM_DECODECHARSET_UTF8);

            isOk = true;

            logger.info(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return isOk;
    }

    public boolean creatMenu(MenuModel menuModel) {
        boolean isOk = false;
        try {
            String url = String.format(WechatApiConstant.MENU_CREATE_POST, WechatConstant.AccessToken);
            // HttpsUtil.post(url, JSON.toJSONString(menuModel));
            String result = null;

            isOk = true;

            logger.info(result);
        } catch (Exception e) {

            logger.error(e.getMessage());
        }
        return isOk;
    }

    private boolean checkResult(String json) {
        Map map = JSON.parseObject(json, Map.class);
        if ("0".equals(map.get("errcode").toString())) {
            return true;
        } else {
            return false;
        }
    }
}
