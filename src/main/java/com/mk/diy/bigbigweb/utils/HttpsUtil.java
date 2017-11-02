package com.mk.diy.bigbigweb.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.util.*;

public class HttpsUtil {
    protected static Logger logger = LoggerFactory.getLogger(HttpsUtil.class);
    public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";
    public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";
    private static final String URL_PARAM_CONNECT_FLAG = "&";
    private static final String EMPTY = "";
    private static MultiThreadedHttpConnectionManager connectionManager = null;
    private static int connectionTimeOut = 25000;
    private static int socketTimeOut = 25000;
    private static int maxConnectionPerHost = 20;
    private static int maxTotalConnections = 20;
    private static HttpClient client;

    public HttpsUtil() {
    }

    public static String post(String url, Map<String, String> params, String encoding) {
        return post(url, params, encoding, (String)null, (String)null);
    }

    public static String post(String url, Map<String, String> params, String encoding, String requestBody) {
        return post(url, params, encoding, (String)null, requestBody);
    }

    private static void addParameters(Map<String, String> params, PostMethod method) {
        if (params != null) {
            Set<String> keySet = params.keySet();
            Iterator var3 = keySet.iterator();

            while(var3.hasNext()) {
                String key = (String)var3.next();
                String value = (String)params.get(key);
                method.addParameter(key, value);
            }
        }

    }

    private static String execute(HttpMethod method, String encoding) throws IOException {
        int statusCode = client.executeMethod(method);
        if (statusCode == 200) {
            return StringUtils.isNotBlank(encoding) ? new String(method.getResponseBody(), encoding) : method.getResponseBodyAsString();
        } else {
            logger.error("Response Status Code = " + method.getStatusCode());
            return null;
        }
    }

    public static String postMultiplePart(String url, Map<String, String> params, String encoding, MultipleUploadPart part) {
        String response = "";
        PostMethod postMethod = null;

        try {
            postMethod = new PostMethod(url);
            addParameters(params, postMethod);
            List<Part> parts = new ArrayList();
            Iterator var7 = part.getFileParts().entrySet().iterator();

            Map.Entry entry;
            while(var7.hasNext()) {
                entry = (Map.Entry)var7.next();
                parts.add(new FilePart((String)entry.getKey(), (File)entry.getValue()));
            }

            var7 = part.getStrParts().entrySet().iterator();

            while(var7.hasNext()) {
                entry = (Map.Entry)var7.next();
                parts.add(new StringPart((String)entry.getKey(), (String)entry.getValue()));
            }

            Part[] p = (Part[])parts.toArray(new Part[parts.size()]);
            MultipartRequestEntity mrp = new MultipartRequestEntity(p, postMethod.getParams());
            postMethod.setRequestEntity(mrp);
            response = execute(postMethod, encoding);
        } catch (IOException var12) {
            logger.error("IOException", var12);
            var12.printStackTrace();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
                postMethod = null;
            }

        }

        return response;
    }

    public static String post(String url, Map<String, String> params, String encoding, String contentType, String requestBody) {
        String response = "";
        PostMethod postMethod = null;

        try {
            postMethod = new PostMethod(url);
            if (StringUtils.isBlank(contentType)) {
                postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
            } else {
                postMethod.setRequestHeader("Content-Type", contentType + ";,charset=" + encoding);
            }

            addParameters(params, postMethod);
            if (StringUtils.isNotBlank(requestBody)) {
                postMethod.setRequestEntity(new StringRequestEntity(requestBody, contentType, encoding));
            }

            response = execute(postMethod, encoding);
        } catch (HttpException var12) {
            logger.error("HttpException", var12);
            var12.printStackTrace();
        } catch (IOException var13) {
            logger.error("IOException", var13);
            var13.printStackTrace();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
                postMethod = null;
            }

        }

        return response;
    }

    public static String get(String url, Map<String, String> params, String encoding) {
        return get(url, params, encoding, (String)null);
    }

    public static String get(String url, Map<String, String> params, String encoding, String contentType) {
        String response = "";
        GetMethod getMethod = null;
        StringBuffer strtTotalURL = new StringBuffer("");
        if (params != null) {
            if (strtTotalURL.indexOf("?") == -1) {
                strtTotalURL.append(url).append("?").append(getUrl(params, encoding));
            } else {
                strtTotalURL.append(url).append("&").append(getUrl(params, encoding));
            }
        }

        logger.debug("GET请求URL = /n" + strtTotalURL.toString());

        try {
            getMethod = new GetMethod(strtTotalURL.toString());
            if (StringUtils.isBlank(contentType)) {
                getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
            } else {
                getMethod.setRequestHeader("Content-Type", contentType + ";charset=" + encoding);
            }

            int statusCode = client.executeMethod(getMethod);
            if (statusCode == 200) {
                if (StringUtils.isNotBlank(encoding)) {
                    response = new String(getMethod.getResponseBody(), encoding);
                } else {
                    response = getMethod.getResponseBodyAsString();
                }
            } else {
                logger.debug("Response Status Code = " + getMethod.getStatusCode());
            }
        } catch (HttpException var12) {
            logger.error("HttpException", var12);
            var12.printStackTrace();
        } catch (IOException var13) {
            logger.error("IOException", var13);
            var13.printStackTrace();
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
                getMethod = null;
            }

        }

        return response;
    }

    public static InputStream getFileInputStream(String url, Map<String, String> params, String encoding) {
        return getFileInputStream(url, params, encoding, (String)null);
    }

    public static InputStream getFileInputStream(String url, Map<String, String> params, String encoding, String contentType) {
        GetMethod getMethod = null;
        StringBuffer strtTotalURL = new StringBuffer(url);
        if (params != null) {
            if (strtTotalURL.indexOf("?") == -1) {
                strtTotalURL.append("?").append(getUrl(params, encoding));
            } else {
                strtTotalURL.append("&").append(getUrl(params, encoding));
            }
        }

        logger.debug("GET请求URL = /n" + strtTotalURL.toString());

        ByteArrayInputStream var8;
        try {
            getMethod = new GetMethod(strtTotalURL.toString());
            if (StringUtils.isBlank(contentType)) {
                getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
            } else {
                getMethod.setRequestHeader("Content-Type", contentType + ";charset=" + encoding);
            }

            int statusCode = client.executeMethod(getMethod);
            if (statusCode != 200) {
                logger.debug("Response Status Code = " + getMethod.getStatusCode());
                return null;
            }

            ByteArrayInputStream i = new ByteArrayInputStream(getMethod.getResponseBody());
            var8 = i;
        } catch (HttpException var13) {
            logger.error("HttpException", var13);
            var13.printStackTrace();
            return null;
        } catch (IOException var14) {
            logger.error("IOException", var14);
            var14.printStackTrace();
            return null;
        } finally {
            if (getMethod != null) {
                getMethod.releaseConnection();
                getMethod = null;
            }

        }

        return var8;
    }

    private static String getUrl(Map<String, String> map, String valueEnc) {
        if (null != map && map.keySet().size() != 0) {
            StringBuffer url = new StringBuffer();
            Set<String> keys = map.keySet();
            Iterator it = keys.iterator();

            while(it.hasNext()) {
                String key = (String)it.next();
                if (map.containsKey(key)) {
                    String val = (String)map.get(key);
                    String str = val != null ? val : "";

                    try {
                        str = URLEncoder.encode(str, valueEnc);
                    } catch (UnsupportedEncodingException var9) {
                        var9.printStackTrace();
                    }

                    url.append(key).append("=").append(str).append("&");
                }
            }

            String strURL = "";
            strURL = url.toString();
            if ("&".equals("" + strURL.charAt(strURL.length() - 1))) {
                strURL = strURL.substring(0, strURL.length() - 1);
            }

            return strURL;
        } else {
            return "";
        }
    }

    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
        connectionManager.getParams().setSoTimeout(socketTimeOut);
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
        connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
        client = new HttpClient(connectionManager);
    }
}