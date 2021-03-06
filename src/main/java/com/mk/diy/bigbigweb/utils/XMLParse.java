/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.mk.diy.bigbigweb.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.converter.CDATAConvert;
import com.mk.diy.bigbigweb.model.WechatRequestModel;
import com.mk.diy.bigbigweb.model.response.ResponseBase;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * XMLParse class
 *
 * 提供提取消息格式中的密文及生成回复消息格式的接口.
 */
public class XMLParse {

    private static XStream xstream;

    static {
        xstream = new XStream(new StaxDriver());
        xstream.autodetectAnnotations(true);
    }

	/**
	 * 提取出xml数据包中的加密消息
	 * @param xmltext 待提取的xml字符串
	 * @return 提取出的加密消息字符串
	 * @throws AesException 
	 */
	public static Object[] extract(String xmltext) throws AesException     {
		Object[] result = new Object[3];
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			StringReader sr = new StringReader(xmltext);
			InputSource is = new InputSource(sr);
			Document document = db.parse(is);

			Element root = document.getDocumentElement();
			NodeList nodelist1 = root.getElementsByTagName("Encrypt");
			NodeList nodelist2 = root.getElementsByTagName("ToUserName");
			result[0] = 0;
			result[1] = nodelist1.item(0).getTextContent();
			result[2] = nodelist2.item(0).getTextContent();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AesException(AesException.ParseXmlError);
		}
	}
    /**
     * 解析xml数据
     * @param xmltext 待提取的xml字符串
     * @return Map<String,String> key-value值
     * @throws AesException
     */
    public static Map<String,String> analysis(String xmltext) throws AesException     {
        try {
            Map<String,String> xmlMap = new HashMap<>();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            StringReader sr = new StringReader(xmltext);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                xmlMap.put(nodeList.item(i).getNodeName(),nodeList.item(i).getTextContent());
            }
            return xmlMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ParseXmlError);
        }
    }

    /**
     * 提取出xml数据包中的加密消息
     * @param ins postdata
     * @return 提取出的加密消息字符串
     * @throws AesException
     */
    public static Object[] extract(InputStream ins) throws AesException     {
        Object[] result = new Object[3];
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(ins);

            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Encrypt");
            NodeList nodelist2 = root.getElementsByTagName("ToUserName");
            result[0] = 0;
            result[1] = nodelist1.item(0).getTextContent();
            result[2] = nodelist2.item(0).getTextContent();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AesException(AesException.ParseXmlError);
        }
    }

    /**
     * 提取出微信请求数据包
     * @param ins postdata
     * @return 提取出的加密消息字符串
     * @throws AesException
     */
    public static WechatRequestModel extractToModel(InputStream ins) throws AesException     {
        Object[] result = extract(ins);

        WechatRequestModel wechatRequestModel = new WechatRequestModel();

        wechatRequestModel.setPostData(result[1].toString());
        wechatRequestModel.setToUserName(result[2].toString());

        return wechatRequestModel;
    }

	/**
	 * 生成xml消息
	 * @param encrypt 加密后的消息密文
	 * @param signature 安全签名
	 * @param timestamp 时间戳
	 * @param nonce 随机字符串
	 * @return 生成的xml字符串
	 */
	public static String generate(String encrypt, String signature, String timestamp, String nonce) {

		String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
				+ "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n"
				+ "<TimeStamp>%3$s</TimeStamp>\n" + "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
		return String.format(format, encrypt, signature, timestamp, nonce);

	}

	public static String generateXmlString(ResponseBase responseBase){
        return StringEscapeUtils.unescapeXml(xstream.toXML(responseBase));
    }

    public static String generateXmlString(Object responseBase){
        return StringEscapeUtils.unescapeXml(xstream.toXML(responseBase));
    }

    public static Object generateObject(InputStream inputStream,Class<WechatRequestModel> c) throws IllegalAccessException, InstantiationException {
        WechatRequestModel wechatRequestModel = c.newInstance();
        return xstream.fromXML(inputStream,wechatRequestModel);
    }

}
