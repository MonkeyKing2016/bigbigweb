package com.mk.diy.bigbigweb.test.util;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatApiConstant;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.test.base.TestBaseConfig;
import com.mk.diy.bigbigweb.utils.HttpsUtil;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author wanghao
 * @create 2017-10-19 14:09
 */
public class TestWechat extends TestBaseConfig{

    @Test
    public void testGetToken(){
        String url = WechatApiConstant.GET_TOKEN;
        url = String.format(url,"client_credential", WechatConstant.AppId,WechatConstant.AppSecret);
        String result = HttpsUtil.get(url, null,HttpsUtil.URL_PARAM_DECODECHARSET_UTF8);
        String token = "MR3oXcNSS_GnJIY-y1rKwyzR3XnTeCcByERqFpx9FmMkRB94siV_GU3sez_arvx3N_GsHG3MLPBUIwof72-dh1QWI5tQBWK4HOe1pL2k9hCAiYctVHYfNiVqVdwYbnlsREGfAAAWJL";
        System.out.println(result);
    }

    @Test
    public void testMap(){
        System.out.println(JSON.toJSONString(WechatConstant.Map));
    }

    @Test
    public void testGetOpenId(){
        String url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=%s&grant_type=refresh_token&refresh_token=%s";
        String re = "-lgGiX_bb82-lkP0WFUsCSlbqQxSL3nziJZ25i6vhXs0Yh7gzDUU25wXxhVaveFuQFCfw-OP6DqmBKWsnxJRyZ4-fL6yR8wgiQy1VbdZCRs";
        String refresh_token = "WQXcMX6D7QmvkJta8ViYcJ9QrEqQulx1-ZpfP8F5ql6Yt3h6g7Ks9MC-wdePFiTcIeCAHHZa3K4WR03Pb2XYZhG2VyjD3qXRlFsf8hI8EUs";
        url = String.format(url, WechatConstant.AppId, refresh_token);
        String result = HttpsUtil.get(url, null,HttpsUtil.URL_PARAM_DECODECHARSET_UTF8);
        System.out.println(result);
    }

    @Test
    public void testMediaGet() throws Exception {
        String url = WechatApiConstant.MEDIA_GET_GET;
        url = String.format(url, "MR3oXcNSS_GnJIY-y1rKwyzR3XnTeCcByERqFpx9FmMkRB94siV_GU3sez_arvx3N_GsHG3MLPBUIwof72-dh1QWI5tQBWK4HOe1pL2k9hCAiYctVHYfNiVqVdwYbnlsREGfAAAWJL", "734iHV_4IJm-Jk6FdCpYBF8C1xCUUg80W_-IBXcNsDYWD3Q4WKjmDYU6GMZSOhQf");
        InputStream inputStream = HttpsUtil.getFileInputStream(url,null,HttpsUtil.URL_PARAM_DECODECHARSET_UTF8, "image/jpeg");
        FileOutputStream out = new FileOutputStream("G:\\qrcode\\123.jpg");
        byte buf[]=new byte[1024];
        int len;
        while((len=inputStream.read(buf))>0){
            out.write(buf,0,len);
        }
        out.close();
        inputStream.close();
        System.out.println("SUCCESS");
    }
}
