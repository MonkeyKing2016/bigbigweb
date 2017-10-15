package com.mk.diy.bigbigweb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
