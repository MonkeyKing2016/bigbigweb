package com.mk.diy.bigbigweb.converter;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.utils.XMLParse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

/**
 * XStream xml 消息转换器
 *
 * @author wanghao
 * @create 2017-10-16 16:17
 */
public class XStreamHttpMessageConvert extends AbstractXmlHttpMessageConverter{
    private Logger logger = LoggerFactory.getLogger(XStreamHttpMessageConvert.class);
    @Override
    protected Object readFromSource(Class aClass, HttpHeaders httpHeaders, Source source) throws IOException {
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("========开始执行==========");
        logger.info(" 开始执行 转换器");
        StreamSource streamSource = null;
        if (!(source instanceof StreamSource)) {
            throw new IllegalArgumentException("参数错误！");
        }
        streamSource = (StreamSource) source;
        Object generateObject = XMLParse.generateObject(streamSource.getInputStream());
        // TODO 去掉注释
        logger.info(" 执行结束 转换器");
        logger.info(JSON.toJSONString(generateObject));
        System.out.println(JSON.toJSONString(generateObject));
        System.out.println("========执行完毕==========");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
        return generateObject;
    }

    @Override
    protected void writeToResult(Object o, HttpHeaders httpHeaders, Result result) throws IOException {

    }

    @Override
    protected boolean supports(Class aClass) {
        throw new UnsupportedOperationException();
    }
}
