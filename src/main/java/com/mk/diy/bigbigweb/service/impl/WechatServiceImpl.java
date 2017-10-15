package com.mk.diy.bigbigweb.service.impl;

import com.mk.diy.bigbigweb.logic.wechat.WechatHandle;
import com.mk.diy.bigbigweb.service.IWechatService;
import com.mk.diy.bigbigweb.utils.AesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 微信service 服务实现类
 *
 * @author wanghao
 * @create 2017-10-15 11:50
 */
@Service
public class WechatServiceImpl implements IWechatService{

    @Autowired
    private WechatHandle wechatHandle;

    @Override
    public String processRequest(String xmlText) throws AesException {
        return wechatHandle.processRequest(xmlText);
    }
}
