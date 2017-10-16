package com.mk.diy.bigbigweb.logic.wechat;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.dao.IUserDao;
import com.mk.diy.bigbigweb.model.WCUserModel;
import com.mk.diy.bigbigweb.model.request.*;
import com.mk.diy.bigbigweb.model.response.TextResponse;
import com.mk.diy.bigbigweb.utils.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 消息处理handle
 *
 * @author wanghao
 * @create 2017-10-15 14:11
 */
@Repository
public class MsgHandle {
    Logger logger = LoggerFactory.getLogger(MsgHandle.class);

    @Autowired
    private IUserDao userDao;

    private static final String SUCCESS = "success";

    public String process(TextMsg textMsg) {
        logger.info(String.format("process textMsg start... param:%s", JSON.toJSONString(textMsg)));
        String userName = textMsg.getFromUserName();
        String content = textMsg.getContent();
        WCUserModel user = userDao.getUser(userName);
        if (user != null) {
            logger.info(String.format("getUser:%s", JSON.toJSONString(user)));
        }
        String result = defualtResp(textMsg.getFromUserName(), textMsg.getToUserName(), String.format("[ %s ]", content));
        logger.info(String.format("processSubscribe end..."));
        return result;
    }

    public String process(ImageMsg imageMsg) {
        return defualtResp(imageMsg.getFromUserName(),imageMsg.getToUserName());
    }

    public String process(LinkMsg linkMsg) {
        return defualtResp(linkMsg.getFromUserName(),linkMsg.getToUserName());
    }

    public String process(LocationMsg locationMsg) {
        return defualtResp(locationMsg.getFromUserName(),locationMsg.getToUserName());
    }

    public String process(VideoMsg videoMsg) {
        return defualtResp(videoMsg.getFromUserName(),videoMsg.getToUserName());
    }

    public String process(VoiceMsg voiceMsg) {
        return defualtResp(voiceMsg.getFromUserName(),voiceMsg.getToUserName());
    }

    public String process(LocationEvent locationEvent) {
        return defualtResp(locationEvent.getFromUserName(),locationEvent.getToUserName());
    }

    public String processSubscribe(SubscribeEvent subscribeEvent) {
        logger.info(String.format("processSubscribe start... param:%s", JSON.toJSONString(subscribeEvent)));
        String userName = subscribeEvent.getFromUserName();
        WCUserModel userModel = new WCUserModel();
        userModel.setWechatNo(userName);
        userModel.setCreateTime(String.valueOf(new Date().getTime()));
        userDao.saveUser(userModel);
        String content = "恭喜你获得该订阅号究极权限！";
        String result = defualtResp(subscribeEvent.getFromUserName(),subscribeEvent.getToUserName(),content);
        logger.info(String.format("processSubscribe end..."));
        return result;
    }

    public String processUnsubsribe(SubscribeEvent subscribeEvent) {
        logger.info(String.format("processUnsubsribe start... param:%s", JSON.toJSONString(subscribeEvent)));
        String userName = subscribeEvent.getFromUserName();
        Integer result = userDao.deleteUser(userName);
        if (result > 0) {
            logger.info(String.format("processUnsubsribe success..."));
        }
        logger.info(String.format("processUnsubsribe end..."));
        return SUCCESS;
    }

    public String processScan(SubscribeEvent subscribeEvent) {
        return SUCCESS;
    }

    public String processClick(MenuEvent menuEvent) {
        return SUCCESS;
    }

    public String processView(MenuEvent menuEvent) {
        return SUCCESS;
    }

    private String defualtResp(String toUserName, String formUserName){
        TextResponse resp = new TextResponse();
        resp.setToUserName(toUserName);
        resp.setFromUserName(formUserName);
        resp.setMsgType(WechatConstant.RESP_MSG_TYPE_TEXT);
        resp.setCreateTime(new Date().getTime());
        resp.setContent("感谢您的评论，该订阅号正在开发中...");
        return XMLParse.generateXmlString(resp);
    }
    private String defualtResp(String toUserName, String formUserName, String content){
        TextResponse resp = new TextResponse();
        resp.setToUserName(toUserName);
        resp.setFromUserName(formUserName);
        resp.setMsgType(WechatConstant.RESP_MSG_TYPE_TEXT);
        resp.setCreateTime(new Date().getTime());
        resp.setContent(content);
        return XMLParse.generateXmlString(resp);
    }
}
