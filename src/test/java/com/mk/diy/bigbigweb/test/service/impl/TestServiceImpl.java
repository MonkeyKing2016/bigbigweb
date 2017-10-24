package com.mk.diy.bigbigweb.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.enums.MenuType;
import com.mk.diy.bigbigweb.model.request.CustomSendMsg;
import com.mk.diy.bigbigweb.model.request.CustomSendText;
import com.mk.diy.bigbigweb.model.request.MenuButton;
import com.mk.diy.bigbigweb.model.request.MenuModel;
import com.mk.diy.bigbigweb.service.IWechatService;
import com.mk.diy.bigbigweb.test.base.TestBaseConfig;
import com.mk.diy.bigbigweb.utils.AesException;
import com.mk.diy.bigbigweb.utils.WechatMenuUtil;
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
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void creatMenu() {
        MenuModel menuModel = new MenuModel();

        MenuButton button1 = WechatMenuUtil.createButton(MenuType.CLICK,"点击事件","i_am_a_key_001");
        MenuButton button3 = WechatMenuUtil.createButton(MenuType.SCANCODE_PUSH,"扫码事件","i_am_a_key_002");
        MenuButton button4 = WechatMenuUtil.createButton(MenuType.SCANCODE_WAITMSG,"扫码消息接受","i_am_a_key_003");

        MenuButton button5 = WechatMenuUtil.createButton(MenuType.PIC_SYSPHOTO,"拍照/摄像","i_am_a_key_004");
        MenuButton button6 = WechatMenuUtil.createButton(MenuType.PIC_PHOTO_OR_ALBUM,"拍照/相册","i_am_a_key_005");
        MenuButton button7 = WechatMenuUtil.createButton(MenuType.PIC_WEIXIN,"相册","i_am_a_key_006");
        MenuButton button8 = WechatMenuUtil.createButton(MenuType.LOCATION_SELECT,"上报地址","i_am_a_key_007");

        MenuButton button2 = WechatMenuUtil.createButton(MenuType.VIEW,"静默跳转","https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=https%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdap" +
                "ter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_bas" +
                "e&state=123#wechat_redirect");
        MenuButton button9 = WechatMenuUtil.createButton(MenuType.MEDIA_ID,"MEDIA_ID","media_id");
        MenuButton button10 = WechatMenuUtil.createButton(MenuType.VIEW_LIMITED,"VIEW_LIMITED","media_id");
        MenuButton button11 = WechatMenuUtil.createButton(MenuType.MINIPROGRAM,"MINIPROGRAM","小程序url","appid","pagepath");

        List<MenuButton> subButtonList = null;
        MenuButton today = WechatMenuUtil.createMainButton("今日热门");
        subButtonList = new ArrayList<>();
        subButtonList.add(button1);
        subButtonList.add(button3);
        subButtonList.add(button4);
        today.setSub_button(subButtonList);

        MenuButton recommend = WechatMenuUtil.createMainButton("精彩推荐");
        subButtonList = new ArrayList<>();
        subButtonList.add(button5);
        subButtonList.add(button6);
        subButtonList.add(button7);
        subButtonList.add(button8);
        recommend.setSub_button(subButtonList);


        MenuButton we = WechatMenuUtil.createMainButton("关于我们");
        subButtonList = new ArrayList<>();
        subButtonList.add(button2);
//        subButtonList.add(button9);
//        subButtonList.add(button10);
//        subButtonList.add(button11);
        we.setSub_button(subButtonList);

        subButtonList = new ArrayList<>();
        subButtonList.add(today);
        subButtonList.add(recommend);
        subButtonList.add(we);
        menuModel.setButton(subButtonList);

        System.out.println(JSON.toJSONString(menuModel));

        boolean msg = wechatService.creatMenu(menuModel);
        System.out.println(msg);
    }

}
