package com.mk.diy.bigbigweb.model;

import com.mk.diy.bigbigweb.converter.CDATAConvert;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;

/**
 * 微信响应报文model
 *
 * @author wanghao
 * @create 2017-10-17 15:17
 */
@XStreamAlias("xml")
public class WechatResponseModel implements Serializable {
    private static final long serialVersionUID = -3981859621341285242L;

    public WechatResponseModel() {

    }

    public WechatResponseModel(String encrypt, String msgSignature, String timeStamp, String nonce) {
        this.encrypt = encrypt;
        this.msgSignature = msgSignature;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
    }

    @XStreamAlias("Encrypt")
    @XStreamConverter(CDATAConvert.class)
    private String encrypt;

    @XStreamAlias("MsgSignature")
    @XStreamConverter(CDATAConvert.class)
    private String msgSignature;

    @XStreamAlias("TimeStamp")
    private String timeStamp;

    @XStreamAlias("Nonce")
    @XStreamConverter(CDATAConvert.class)
    private String nonce;

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }

    public String getMsgSignature() {
        return msgSignature;
    }

    public void setMsgSignature(String msgSignature) {
        this.msgSignature = msgSignature;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

}
