package com.mk.diy.bigbigweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 主页controller
 *
 * @author wanghao
 * @create 2017-10-14 10:46
 */
@Controller()
@RequestMapping("/bbk")
public class IndexController {
    Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("index");
        logger.info("index 执行");
        return mv;
    }
    @RequestMapping(value = "/connect", method = {RequestMethod.GET})
    public ModelAndView get(){
        logger.info("get 执行");
        System.out.println("get");
        return new ModelAndView("index");
    }
    @RequestMapping(value = "/connect", method = {RequestMethod.POST})
    public ModelAndView post(){
        logger.info("post 执行");
        System.out.println("post");
        return new ModelAndView("index");
    }
}
