package com.mk.diy.bigbigweb.model.request;

import com.mk.diy.bigbigweb.model.request.RequestBase;

/**
 * 接受事件推送 base model
 *
 * @author wanghao
 * @create 2017-10-15 12:17
 */
public abstract class EventBase extends RequestBase {
    private static final long serialVersionUID = -7010957364107120817L;

    protected String event;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
