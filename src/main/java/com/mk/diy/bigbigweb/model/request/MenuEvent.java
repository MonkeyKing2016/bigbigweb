package com.mk.diy.bigbigweb.model.request;

/**
 * 菜单 事件
 *
 * @author wanghao
 * @create 2017-10-15 12:40
 */
public class MenuEvent extends EventBase {
    private static final long serialVersionUID = -8917506662772212262L;
    private String eventKey;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
}
