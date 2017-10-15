package com.mk.diy.bigbigweb.model.request;

import com.mk.diy.bigbigweb.model.request.RequestBase;


/**
 * 接受普通消息model
 *
 * @author wanghao
 * @create 2017-10-15 12:15
 */
public abstract class MsgBase extends RequestBase{
    private static final long serialVersionUID = 6739319259401687132L;

    protected Long msgId;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}
