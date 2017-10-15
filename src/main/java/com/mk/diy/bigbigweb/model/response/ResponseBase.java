package com.mk.diy.bigbigweb.model.response;

import com.mk.diy.bigbigweb.converter.CDATAConvert;
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
    @XStreamConverter(CDATAConvert.class)
    protected String ToUserName;
    @XStreamConverter(CDATAConvert.class)
    protected String FromUserName;
    protected Long CreateTime;
    @XStreamConverter(CDATAConvert.class)
    protected String MsgType;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
