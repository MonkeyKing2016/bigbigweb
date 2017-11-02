package com.mk.diy.bigbigweb.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

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

    public void getJsapiTicket(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        Map<String, String> map = null;
        try {
            out = response.getWriter();

            String url = request.getParameter("url");

            if (StringUtils.isEmpty(url)) {
                throw new IllegalArgumentException("url 参数不能为空");
            }

            // 不包含#及其后面部分
            url = url.split("#")[0];

            // 获取 jsapi_ticket
            String ticket = null;
//            ticket = weChatService.getTicket();

            map = Sign.sign(ticket, url);

        } catch (Exception e) {
            logger.error("系统异常：{}",e.getMessage());
        } finally {
            if (map != null) {
                out.write(JSON.toJSONString(map));
            }
            out.close();
        }

    }
}
