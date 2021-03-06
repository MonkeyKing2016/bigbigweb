package com.mk.diy.bigbigweb.controller;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.model.WechatRequestModel;
import com.mk.diy.bigbigweb.model.WechatResponseModel;
import com.mk.diy.bigbigweb.service.IWechatService;
import com.mk.diy.bigbigweb.utils.AesException;
import com.mk.diy.bigbigweb.utils.WXBizMsgCrypt;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微信交互controller
 *
 * @author wanghao
 * @create 2017-10-14 16:01
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    Logger logger = LoggerFactory.getLogger(WechatController.class);

    @Autowired
    private IWechatService wechatService;

    @RequestMapping(value="/connect.do",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void connect(HttpServletRequest request, HttpServletResponse response) throws IOException{
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；

        boolean isGet = request.getMethod().toLowerCase().equals("get");

        String signature = null;
        String timestamp = null;
        String nonce = null;
        String echostr = null;
        String result = null;
        String msg_signature = null;
        WXBizMsgCrypt crypt = null;

        PrintWriter out = response.getWriter();

        try {

            signature = request.getParameter("signature");// 微信加密签名
            msg_signature = request.getParameter("msg_signature");// 微信加密签名
            timestamp = request.getParameter("timestamp");// 时间戳
            nonce = request.getParameter("nonce");// 随机数
            echostr = request.getParameter("echostr");//随机字符串

            crypt = new WXBizMsgCrypt(WechatConstant.Token, WechatConstant.EncodingAESKey,WechatConstant.AppId);

            if (isGet) {

                // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
                result = crypt.access(signature, timestamp, nonce, echostr);

            }else{

                result = crypt.decryptMsg(msg_signature, timestamp, nonce, request.getInputStream());

                result = wechatService.processRequest(result);

                result = crypt.encryptMsg(result, timestamp, nonce);

            }
        } catch (AesException aes) {
            processAesException(signature, timestamp, nonce, echostr, aes);
        } catch (Exception e) {
            logger.error(String.format("未知错误！！！.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
        } finally {
            response.getWriter().write(result);
            out.close();
        }
    }

    @RequestMapping(value="/devConverter.do",method = {RequestMethod.GET, RequestMethod.POST}, consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.TEXT_XML_VALUE},produces = {MediaType.TEXT_XML_VALUE} )
    @ResponseBody
    public String devConverter(@RequestBody WechatRequestModel wechatRequestModel,HttpServletRequest request) throws IOException, AesException {
        logger.info(JSON.toJSONString(wechatRequestModel));
        WXBizMsgCrypt crypt = new WXBizMsgCrypt(WechatConstant.Token, WechatConstant.EncodingAESKey,WechatConstant.AppId);
        String msg_signature = request.getParameter("msg_signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");// 随机数
        String decryptMsg = crypt.decryptMsg(msg_signature, timestamp, nonce, wechatRequestModel.getPostData());

        decryptMsg = wechatService.processRequest(decryptMsg);

        decryptMsg = crypt.encryptMsg(decryptMsg, timestamp, nonce);

        logger.info(decryptMsg);
        return decryptMsg;
    }

    @RequestMapping(value="/dev.do",method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void dev(HttpServletRequest request, HttpServletResponse response) throws IOException{
        logger.info("request getContentType :{}",request.getContentType());
        logger.info(String.format("request getContentType : %s",request.getContentType()));
        byte[] bytes = new byte[1024];
        ServletInputStream inputStream = request.getInputStream();
        int rc = 0;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((rc = inputStream.read(bytes, 0, 100)) > 0) {
            outputStream.write(bytes, 0, rc);
        }
        bytes = outputStream.toByteArray();
        String result = new String(Base64.encodeBase64(bytes));
        logger.info("result : {}",result);
        logger.info(String.format("result : %s",result));
    }

    private void processAesException(String signature, String timestamp, String nonce, String echostr, AesException aes) {
        switch (aes.getCode()) {
            case AesException.ValidateSignatureError:
                logger.error(String.format("签名验证错误.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
                break;
            case AesException.ParseXmlError:
                logger.error(String.format("xml解析失败.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
                break;
            case AesException.ComputeSignatureError:
                logger.error(String.format("sha加密生成签名失败.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
                break;
            case AesException.IllegalAesKey:
                logger.error(String.format("SymmetricKey非法.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
                break;
            case AesException.ValidateAppidError:
                logger.error(String.format("appid校验失败.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
                break;
            case AesException.EncryptAESError:
                logger.error(String.format("aes加密失败.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
                break;
            case AesException.DecryptAESError:
                logger.error(String.format("aes解密失败.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
                break;
            case AesException.IllegalBuffer:
                logger.error(String.format("解密后得到的buffer非法.signature:%s,timestamp:%s,nonce:%s,echostr:%s",signature,timestamp,nonce,echostr));
                break;
            default:
                logger.error(String.format("null==>errorCode:%s.signature:%s,timestamp:%s,nonce:%s,echostr:%s",aes.getCode(),signature,timestamp,nonce,echostr));
                break;
        }
    }
}
