package com.mk.diy.bigbigweb.test.dao.impl;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.dao.IUserDao;
import com.mk.diy.bigbigweb.model.UserModel;
import com.mk.diy.bigbigweb.model.request.TextMsg;
import com.mk.diy.bigbigweb.model.response.TextResponse;
import com.mk.diy.bigbigweb.test.base.TestBaseConfig;
import com.mk.diy.bigbigweb.test.model.Student;
import com.mk.diy.bigbigweb.test.model.TestBase;
import com.mk.diy.bigbigweb.test.model.TestCat;
import com.mk.diy.bigbigweb.test.model.TestXmlModel;
import com.mk.diy.bigbigweb.test.util.TestXstreamUtil;
import com.mk.diy.bigbigweb.utils.AesException;
import com.mk.diy.bigbigweb.utils.WXBizMsgCrypt;
import com.mk.diy.bigbigweb.utils.XMLParse;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.dom4j.DocumentException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.ValidationException;
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
public class TestUserDaoImpl extends TestBaseConfig{
    Logger logger = LoggerFactory.getLogger(TestUserDaoImpl.class);

    @Autowired
    private IUserDao userDao;


    @Test
    public void getSHA1() throws AesException {
        String signature = "f5219b366012f2925f0414f04a427b14219c661d";
        String timeStamp = "1507982068";
        String nonce = "2072117863";
        String echoStr = "9526807232250773495";
        WXBizMsgCrypt crypt = new WXBizMsgCrypt(WechatConstant.Token, WechatConstant.EncodingAESKey,WechatConstant.AppId);
        String access = crypt.access(signature, timeStamp, nonce, echoStr);
        System.out.println(access);
    }
    @Test
    public void encrypt() throws AesException {
        String encrypt = "c330DzW7VOaUhQS0DpsZBejWv15RWRUuuf1SpVa7aS3sOuLPZKCI3jIgxMZoeyJ//3GjWkjqzJBmx0SNghquDW6fNikl44ugmaz31Qs2qvbwgd1y6wL56X0I/Ue7ai0QFj1IW/BMQ0W4Hf6Bc/FIxeNEqsnQwvrBBR+5qC8Ge0xBfdGZ03Q7TWnJuMpHGuFN8Xl9lC9hUQp+zvCdpKuREL1GjizTJaqRPCtBkSjyQ4fNdzHv/qINM379/uG7xg8EfYRXyJoM301Ld0N+CBebiVio3ycty5ermyn34B2iMjB5mC3fJeap6erkp9odZovWTKzm4RYsOXJTUpd+sD/kFEx/xkinDs7K+PCP7gnYfcL++amSeNZhlaJ9MQoCsLFdY5qgR7UFNKtpLHgRfNwpwnV1U1b2UNiUuhZHfcWyfCM=";
        String signature = "25c0c40f3c660cdd6dd912230eaa83a372318eaf";
        String timeStamp = "1507985595";
        String nonce = "2067810837";

        WXBizMsgCrypt crypt = new WXBizMsgCrypt(WechatConstant.Token, WechatConstant.EncodingAESKey,WechatConstant.AppId);

        String decrypt = crypt.decrypt(encrypt);
        System.out.println(decrypt);
    }
    @Test
    public void testBase(){
        TestCat cat = new TestCat();
        cat.setId("111");
        cat.setName("222");
        TestBase base = cat;
        System.out.println(JSON.toJSONString(base));
    }

    @Test
    public void testxml() throws DocumentException, IOException, SAXException, ParserConfigurationException {
        TestXstreamUtil util = new TestXstreamUtil();
        Map<String, String> dom = util.paseDom("<xml>" +
                "<ToUserName><![CDATA[gh_7f12c64e476e]]></ToUserName>" +
                "<FromUserName><![CDATA[okQF5jgnS7mKOLNSt9hlsMf4PYOQ]]></FromUserName>" +
                "<CreateTime>1507987206</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[哭哭哭]]></Content>" +
                "<MsgId>6476755732982773713</MsgId>" +
                "</xml>");
        Map<String, String> map = util.pase("<xml>" +
                "<ToUserName><![CDATA[gh_7f12c64e476e]]></ToUserName>" +
                "<FromUserName><![CDATA[okQF5jgnS7mKOLNSt9hlsMf4PYOQ]]></FromUserName>" +
                "<CreateTime>1507987206</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[哭哭哭]]></Content>" +
                "<MsgId>6476755732982773713</MsgId>" +
                "</xml>");
        long time = new Date().getTime();
        TextMsg textMsg = JSON.parseObject(JSON.toJSONString(dom),TextMsg.class);
        long time2 = new Date().getTime();
        map.put("hehe","2");
        long time3 = new Date().getTime();
        TextMsg textMsg2 = JSON.parseObject(JSON.toJSONString(map),TextMsg.class);
        long time4 = new Date().getTime();
        System.out.println("1 time:"+(time2-time));
        System.out.println("2 time:"+(time4-time3));
    }
    @Test
    public void testxml2() throws DocumentException {
        TestXstreamUtil util = new TestXstreamUtil();
        util.paseDom("<xml>" +
                "<ToUserName><![CDATA[gh_7f12c64e476e]]></ToUserName>" +
                "<FromUserName><![CDATA[okQF5jgnS7mKOLNSt9hlsMf4PYOQ]]></FromUserName>" +
                "<CreateTime>1507987206</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[哭哭哭]]></Content>" +
                "<MsgId>6476755732982773713</MsgId>" +
                "</xml>");
    }

    @Test
    public void testxml3() throws DocumentException, JAXBException, IOException {

        TextResponse response = new TextResponse();
        response.setContent("wocao?");
        response.setCreateTime(new Date().getTime());
        response.setFromUserName("最帅没有之一");
        response.setToUserName("wangshuai");
        response.setMsgType(WechatConstant.RESP_MSG_TYPE_TEXT);
        String s = XMLParse.generateXmlString(response);
        System.out.println(s);


    }

    @Test
    public void testCastor() throws ValidationException {
        String xml = "<xml><ToUserName><![CDATA[gh_7f12c64e476e]]></ToUserName>\n" +
                "<FromUserName><![CDATA[okQF5jgnS7mKOLNSt9hlsMf4PYOQ]]></FromUserName>\n" +
                "<CreateTime>1507985595</CreateTime>\n" +
                "<MsgType><![CDATA[text]]></MsgType>\n" +
                "<Content><![CDATA[我日]]></Content>\n" +
                "<MsgId>6476748813790459240</MsgId>\n" +
                "</xml>";
        XStream xStream = new XStream(new StaxDriver());
        xStream.autodetectAnnotations(true);
        Student student = new Student();
        student.setStudentName("sianx");
        student.setType(10086);
        String toXML = xStream.toXML(student);
        System.out.println(toXML);
    }


}
