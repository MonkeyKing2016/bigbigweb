package com.mk.diy.bigbigweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * wechat后台控制controller
 *
 * @author wanghao
 * @create 2017-10-17 17:08
 */
@Controller
public class WechatAdminController {

    @RequestMapping("/wechat/getToken")
    public void getToken(@RequestParam("authCode") String authCode){

    }
    @RequestMapping("/wechat/createMenu")
    public void createMenu(@RequestParam("authCode") String authCode){

    }
    @RequestMapping("/wechat/findMenu")
    public void findMenu(@RequestParam("authCode") String authCode){

    }
    @RequestMapping("/wechat/postMsg")
    public void postMsg(@RequestParam("authCode") String authCode){

    }
}
