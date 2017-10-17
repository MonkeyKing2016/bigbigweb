package com.mk.diy.bigbigweb.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 微信请求报文model
 *
 * @author wanghao
 * @create 2017-10-17 13:43
 */
@XStreamAlias("xml")
public class WechatRequestModel implements Serializable{
    private static final long serialVersionUID = 990087414660038622L;

    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("Encrypt")
    private String postData;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }
}
