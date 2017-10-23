package com.mk.diy.bigbigweb.test.util;

import com.alibaba.fastjson.JSON;
import com.mk.diy.bigbigweb.constant.WechatApiConstant;
import com.mk.diy.bigbigweb.constant.WechatConstant;
import com.mk.diy.bigbigweb.test.base.TestBaseConfig;
import com.mk.diy.bigbigweb.utils.HttpsUtil;
import org.junit.Test;

/**
 * @author wanghao
 * @create 2017-10-19 14:09
 */
public class TestWechat extends TestBaseConfig{

    @Test
    public void testGetToken(){
        String url = WechatApiConstant.GET_TOKEN;
        url = String.format(url,"client_credential", WechatConstant.AppId,WechatConstant.AppSecret);
        String result = HttpsUtil.get(url, null);
        String token = "Zk7ufBXGrsI_VAEO_LlpeAI9lDk-k3frVJQ02ElAj_AbKu1ZL9UYEngJTAwO7MMMoTuvEqVwaULmBj_z4c8uNqA7tFZ3zvwyMi5IJ_cmiRYMXIhAFAHSD";
        System.out.println(result);
    }

    @Test
    public void testMap(){
        System.out.println(JSON.toJSONString(WechatConstant.Map));
    }
}
