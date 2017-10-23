package com.mk.diy.bigbigweb.logic.wechat;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.dao.IUserDao;
import com.mk.diy.bigbigweb.enums.MsgEnum;
import com.mk.diy.bigbigweb.model.WCUserModel;
import com.mk.diy.bigbigweb.model.request.*;
import com.mk.diy.bigbigweb.model.response.TextResponse;
import com.mk.diy.bigbigweb.utils.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
        if (StringUtils.isEmpty(content)){
            content = "您没有说话";
        }
        else if (content.startsWith("绑定账号")){
            // 绑定账户
            String userNo = content.substring(4,content.length());
            if (StringUtils.isEmpty( userNo) || userNo.length() <= 6) {
                content = "需要绑定账号为空或者长度小于6位";
            } else {
                // TODO 检查用户是否存在
                String userTempName = "";
                M : for (String key : WechatConstant.Map.keySet()) {
                    String value = WechatConstant.Map.get(key);
                    if (userName.equals(value)) {
                        userTempName = value;
                        break M;
                    }
                }
                String wechatNo = WechatConstant.Map.get(userNo);
                if ( !StringUtils.isEmpty(userTempName) && userName.equals(wechatNo) ) {
                    content = String.format("该账号:%s 已绑定此微信.需要解绑请输入解绑账号XXXX",userNo);
                }
                else if (!StringUtils.isEmpty(userTempName) && !userName.equals(wechatNo)){
                    content = String.format("该账号:%s 已被其他账号绑定.或者该微信号已绑定其他账号",userNo);
                }
                else {
                    WechatConstant.Map.put(userNo,userName);
                    content = "绑定成功";
                }
            }
        }
        else if (content.startsWith("解绑账号")){
            // 绑定账户
            String userNo = content.substring(4,content.length());
            if (StringUtils.isEmpty( userNo) || userNo.length() <= 6) {
                content = "需要解绑账户为空或者长度小于6位";
            } else {
                String userTempName = "";
                M : for (String key : WechatConstant.Map.keySet()) {
                    String value = WechatConstant.Map.get(key);
                    if (userName.equals(value) && userNo.equals(key)) {
                        userTempName = value;
                        break M;
                    }
                }
                if (StringUtils.isEmpty(userTempName)) {
                    content = String.format("该账号:%s 没有绑定.需要绑定请输入绑定账号XXXX",userNo);
                } else{
                    WechatConstant.Map.remove(userNo);
                    content = "解绑成功";
                }
            }
        }
        String result = defualtResp(textMsg.getFromUserName(), textMsg.getToUserName(), String.format("[ %s ]", content));
        logger.info(String.format("processSubscribe end..."));
        return result;
    }

    public String process(ImageMsg imageMsg) {
        return defualtRespByType(imageMsg.getFromUserName(),imageMsg.getToUserName(),imageMsg.getMsgType());
    }

    public String process(LinkMsg linkMsg) {
        return defualtRespByType(linkMsg.getFromUserName(),linkMsg.getToUserName(),linkMsg.getMsgType());
    }

    public String process(LocationMsg locationMsg) {
        return defualtRespByType(locationMsg.getFromUserName(),locationMsg.getToUserName(),locationMsg.getMsgType());
    }

    public String process(VideoMsg videoMsg) {
        return defualtRespByType(videoMsg.getFromUserName(),videoMsg.getToUserName(),videoMsg.getMsgType());
    }

    public String process(VoiceMsg voiceMsg) {
        return defualtRespByType(voiceMsg.getFromUserName(),voiceMsg.getToUserName(),voiceMsg.getMsgType());
    }

    public String process(LocationEvent locationEvent) {
        return defualtRespByType(locationEvent.getFromUserName(),locationEvent.getToUserName(),locationEvent.getEvent());
    }
    // TODO vo需要重新定义
    public String processLocationEvent(LocationEvent locationEvent) {
        return defualtRespByType(locationEvent.getFromUserName(),locationEvent.getToUserName(),locationEvent.getEvent());
    }

    public String processSubscribe(SubscribeEvent subscribeEvent) {
        logger.info(String.format("processSubscribe start... param:%s", JSON.toJSONString(subscribeEvent)));
        String userName = subscribeEvent.getFromUserName();
        WCUserModel userModel = new WCUserModel();
        userModel.setWechatNo(userName);
        userModel.setCreateTime(String.valueOf(new Date().getTime()));
        userDao.saveUser(userModel);
        String content = "需要绑定请回复绑定账号XXXX！例如绑定账号123456则成功绑定123456的账户";
        String result = defualtResp(subscribeEvent.getFromUserName(),subscribeEvent.getToUserName(),content);
        logger.info(String.format("processSubscribe end..."));
        return result;
    }

    public String processUnsubsribe(SubscribeEvent subscribeEvent) {
        logger.info(String.format("processUnsubsribe start... param:%s", JSON.toJSONString(subscribeEvent)));
        String userName = subscribeEvent.getFromUserName();
        Integer result = userDao.deleteUser(userName);

        // 解绑账户
        String userNo = "";
        M : for (String key : WechatConstant.Map.keySet()) {
            String value = WechatConstant.Map.get(key);
            if (userName.equals(value)) {
                userNo = key;
                break M;
            }
        }
        if (!StringUtils.isEmpty(userNo)) {
            WechatConstant.Map.remove(userNo);
            logger.info("解绑成功 userNo{},userName:{}",userNo,userName);
        }

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
        logger.info("账号查询:{}",JSON.toJSONString(WechatConstant.Map));
        System.out.println(String.format("账号查询:%s",JSON.toJSONString(WechatConstant.Map)));
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
    private String defualtRespByType(String toUserName, String formUserName, String type){
        TextResponse resp = new TextResponse();
        resp.setToUserName(toUserName);
        resp.setFromUserName(formUserName);
        resp.setMsgType(WechatConstant.RESP_MSG_TYPE_TEXT);
        resp.setCreateTime(new Date().getTime());
        resp.setContent(String.format("接收到[ %s ]类型的消息.", MsgEnum.getCode(type)));
        return XMLParse.generateXmlString(resp);
    }
}
