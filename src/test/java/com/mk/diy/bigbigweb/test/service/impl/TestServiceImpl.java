package com.mk.diy.bigbigweb.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.dao.IUserDao;
import com.mk.diy.bigbigweb.model.request.TextMsg;
import com.mk.diy.bigbigweb.model.response.TextResponse;
import com.mk.diy.bigbigweb.service.IWechatService;
import com.mk.diy.bigbigweb.test.base.TestBaseConfig;
import com.mk.diy.bigbigweb.test.model.TestBase;
import com.mk.diy.bigbigweb.test.model.TestCat;
import com.mk.diy.bigbigweb.test.util.TestXstreamUtil;
import com.mk.diy.bigbigweb.utils.AesException;
import com.mk.diy.bigbigweb.utils.WXBizMsgCrypt;
import com.mk.diy.bigbigweb.utils.XMLParse;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * test
 *
 * @author wanghao
 * @create 2017-10-14 12:01
 */
public class TestServiceImpl extends TestBaseConfig{
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



}
