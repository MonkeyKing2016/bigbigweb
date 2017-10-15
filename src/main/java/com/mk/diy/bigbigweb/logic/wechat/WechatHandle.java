package com.mk.diy.bigbigweb.logic.wechat;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.model.request.*;
import com.mk.diy.bigbigweb.utils.AesException;
import com.mk.diy.bigbigweb.utils.XMLParse;
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
            case WechatConstant.EVENT_TYPE_SUBSCRIBE :
                result = msgHandle.processSubscribe(JSON.parseObject(JSON.toJSONString(map), SubscribeEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_UNSUBSCRIBE :
                result = msgHandle.processUnsubsribe(JSON.parseObject(JSON.toJSONString(map), SubscribeEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_SCAN :
                result = msgHandle.processScan(JSON.parseObject(JSON.toJSONString(map), SubscribeEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_LOCATION :
                result = msgHandle.process(JSON.parseObject(JSON.toJSONString(map), LocationEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_CLICK :
                result = msgHandle.processClick(JSON.parseObject(JSON.toJSONString(map), MenuEvent.class));
                break;
            case WechatConstant.EVENT_TYPE_VIEW :
                result = msgHandle.processView(JSON.parseObject(JSON.toJSONString(map), MenuEvent.class));
                break;
            default:
                throw new AesException(AesException.UnkownEvent);
        }
        return result;
    }
}
