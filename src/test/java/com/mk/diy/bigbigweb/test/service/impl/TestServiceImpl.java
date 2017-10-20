package com.mk.diy.bigbigweb.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.model.request.CustomSendMsg;
import com.mk.diy.bigbigweb.model.request.CustomSendText;
import com.mk.diy.bigbigweb.service.IWechatService;
import com.mk.diy.bigbigweb.test.base.TestBaseConfig;
import com.mk.diy.bigbigweb.utils.AesException;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * test
 *
 * @author wanghao
 * @create 2017-10-14 12:01
 */
public class TestServiceImpl extends TestBaseConfig {
    Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private IWechatService wechatService;


    @Test
    public void process() throws AesException {
        String s = wechatService.processRequest("<xml>" +
                "<ToUserName><![CDATA[gh_7f12c64e476e]]></ToUserName>" +
                "<FromUserName><![CDATA[okQF5jgnS7mKOLNSt9hlsMf4PYOQ]]></FromUserName>" +
                "<CreateTime>1507987206</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[哭哭哭]]></Content>" +
                "<MsgId>6476755732982773713</MsgId>" +
                "</xml>");
        System.out.println(s);
    }

    @Test
    public void constant() {
        String appId = WechatConstant.AppId;
        System.out.println(appId);
    }
    @Test
    public void getToken() {
        boolean msg = wechatService.getToken();
        System.out.println(msg);
    }
    private void sendMsgProcess(int contentIndex){
        CustomSendMsg sendMsg = new CustomSendMsg("owfNw03FW03t3c8UpVOQeaWG2UuY",WechatConstant.RESP_MSG_TYPE_TEXT);
        CustomSendText sendText = new CustomSendText("哈哈"+contentIndex);
        sendMsg.setText(sendText);
        System.out.println(JSON.toJSONString(sendMsg));
        boolean msg = wechatService.sendMsg(sendMsg);
        System.out.println(msg);
    }
    @Test
    public void sendMsg() {
//        for (int i = 0; i < 10; i++) {
//            sendMsgProcess(i);
//        }
        CustomSendMsg sendMsg = new CustomSendMsg("owfNw03FW03t3c8UpVOQeaWG2UuY",WechatConstant.RESP_MSG_TYPE_TEXT);
        CustomSendText sendText = new CustomSendText("哈哈");
        sendMsg.setText(sendText);
        System.out.println(JSON.toJSONString(sendMsg));
        boolean msg = wechatService.sendMsg(sendMsg);
        System.out.println(msg);
    }

}
