package com.mk.diy.bigbigweb.service;

import com.mk.diy.bigbigweb.model.request.CustomSendMsg;
import com.mk.diy.bigbigweb.model.request.MenuModel;
import com.mk.diy.bigbigweb.utils.AesException;

/**
 * 微信服务
 *
 * @author wanghao
 * @create 2017-10-15 11:50
 */
public interface IWechatService {

    String processRequest(String xmlText) throws AesException;

    boolean getToken();

    boolean sendMsg(CustomSendMsg sendMsg);

    boolean creatMenu(MenuModel menuModel);
}
