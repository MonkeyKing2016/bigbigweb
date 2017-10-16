package com.mk.diy.bigbigweb.model.response;

import com.mk.diy.bigbigweb.converter.CDATAConvert;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;

/**
 * base 响应model
 *
 * @author wanghao
 * @create 2017-10-15 11:52
 */
public abstract class ResponseBase implements Serializable{
    private static final long serialVersionUID = 3259450802639153100L;
    @XStreamAlias("ToUserName")
    @XStreamConverter(CDATAConvert.class)
    protected String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(CDATAConvert.class)
    protected String fromUserName;

    protected Long CreateTime;

    @XStreamAlias("MsgType")
    @XStreamConverter(CDATAConvert.class)
    protected String msgType;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

}
