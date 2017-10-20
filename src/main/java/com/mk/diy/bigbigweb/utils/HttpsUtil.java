package com.mk.diy.bigbigweb.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpsUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpsUtil.class);

    public static final String get(final String url, final Map<String, Object> params) {
//        String paramString = getParamString(params);
//        String newurl = url + paramString;
        CloseableHttpClient httpClient = createSSLClientDefault();

        CloseableHttpResponse response = null;
        HttpGet get = new HttpGet(url);
        String result = "";

        try {
            response = httpClient.execute(get);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
            }
        }

        return result;
    }

    private static String getParamString(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder("");

        if (null != params && !params.isEmpty()) {
            int i = 0;
            for (String key : params.keySet()) {
                if (i == 0) {
                    sb.append("?");
                } else {
                    sb.append("&");
                }
                sb.append(key).append("=").append(params.get(key));
                i++;
            }
        }
        return sb.toString();
    }

    public static final String post(final String url, final String json) {
        CloseableHttpClient httpClient = createSSLClientDefault();

        HttpPost post = new HttpPost(url);

        CloseableHttpResponse response = null;

        if (StringUtils.isNotBlank(json)) {
            post.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
        }

        String result = "";

        try {
            response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
            }
        }

        return result;
    }

    public static final String post(final String url, final Map<String, Object> params) {
        CloseableHttpClient httpClient = createSSLClientDefault();
        HttpPost post = new HttpPost(url);

        CloseableHttpResponse response = null;

        if (null != params && !params.isEmpty()) {
            List<NameValuePair> nvpList = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair nvp = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                nvpList.add(nvp);
            }
            post.setEntity(new UrlEncodedFormEntity(nvpList, Charset.forName("UTF-8")));
        }

        String result = "";

        try {
            response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                if (null != entity) {
                    result = EntityUtils.toString(entity, "UTF-8");
                }
            }
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        } finally {
            if (null != response) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException ex) {
                    logger.error(ex.getMessage());
                }
            }
        }

        return result;
    }

    private static CloseableHttpClient createSSLClientDefault() {

        SSLContext sslContext;
        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                @Override
                public boolean isTrusted(X509Certificate[] xcs, String string) {
                    return true;
                }
            }).build();

            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);

            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyStoreException ex) {
            logger.error(ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex.getMessage());
        } catch (KeyManagementException ex) {
            logger.error(ex.getMessage());
        }

        return HttpClients.createDefault();
    }

    public static void main(String[] args) {


        System.out.println("Result:" + get("https://github.com/", null));
    }
}