package com.mk.diy.bigbigweb.converter;

import com.mk.diy.bigbigweb.utils.XMLParse;
import com.thoughtworks.xstream.XStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.xml.AbstractXmlHttpMessageConverter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;

/**
 * XStream xml 消息转换器
 *
 * @author wanghao
 * @create 2017-10-16 16:17
 */
public class XStreamHttpMessageConvert extends AbstractXmlHttpMessageConverter{

    @Override
    protected Object readFromSource(Class aClass, HttpHeaders httpHeaders, Source source) throws RuntimeException{
        if (!(source instanceof StreamSource)) {
            throw new IllegalArgumentException("参数错误！");
        }

        StreamSource streamSource = (StreamSource) source;

        XStream xStream = new XStream();
        xStream.alias("xml",aClass);
        xStream.autodetectAnnotations(true);

        return xStream.fromXML(streamSource.getInputStream());
    }

    @Override
    protected void writeToResult(Object o, HttpHeaders httpHeaders, Result result) throws RuntimeException, IOException {
        if (!(result instanceof StreamResult)) {
            throw new IllegalArgumentException("参数错误！");
        }

        StreamResult streamResult = (StreamResult) result;

        String xmlString = "SUCCESS";

        // 此处可以定义 处理的类型
        if (o instanceof String) {
            xmlString = (String) o;
        } else {
            xmlString = XMLParse.generateXmlString(o);
        }

        streamResult.getOutputStream().write(xmlString.getBytes());
    }

    @Override
    protected boolean supports(Class aClass) {
        // 默认为true
        if (aClass != null){
            return true;
        } else {
            throw new UnsupportedOperationException("转换对象为空...");
        }
    }
}
