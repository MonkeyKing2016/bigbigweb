package com.mk.diy.bigbigweb.test.util;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.model.request.TextMsg;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wanghao
 * @create 2017-10-15 13:05
 */
public class TestXstreamUtil {

    public Map<String,String> pase(String xml) throws ParserConfigurationException, IOException, SAXException {
        long start = new Date().getTime();
        Map<String,String> xmlMap = new HashMap<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(xml);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);
        Element root = document.getDocumentElement();
        NodeList nodeList = root.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            xmlMap.put(nodeList.item(i).getNodeName(),nodeList.item(i).getTextContent());
        }
        long end = new Date().getTime();
        System.out.println("w3c:"+(end-start));
        return xmlMap;
    }

    public Map<String,String> paseDom(String xml) throws DocumentException {
        long start = new Date().getTime();
        Map<String,String> xmlMap = new HashMap<>();
        org.dom4j.Document document = DocumentHelper.parseText(xml);
        org.dom4j.Element root = document.getRootElement();
        List<org.dom4j.Element> elements = root.elements();
        for (org.dom4j.Element element : elements) {
            xmlMap.put(element.getName(), String.valueOf(element.getData()));
        }
        long end = new Date().getTime();
        System.out.println("dom4j:"+(end-start));
        return xmlMap;
    }
}
