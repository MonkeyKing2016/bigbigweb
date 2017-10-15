package com.mk.diy.bigbigweb.model.request;


import java.io.Serializable;

/**
 * base 请求model
 *
 * @author wanghao
 * @create 2017-10-15 11:53
 */
public abstract class RequestBase implements Serializable{
    private static final long serialVersionUID = 2052557372100794750L;

    protected String toUserName;
    protected String fromUserName;
    protected Long createTime;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
