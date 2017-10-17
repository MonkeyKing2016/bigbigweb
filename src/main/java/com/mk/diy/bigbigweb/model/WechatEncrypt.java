package com.mk.diy.bigbigweb.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Encrypt")
public class WechatEncrypt {
    private String xml;
    private String hehe;

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getHehe() {
        return hehe;
    }

    public void setHehe(String hehe) {
        this.hehe = hehe;
    }
}