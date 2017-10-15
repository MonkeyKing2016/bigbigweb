package com.mk.diy.bigbigweb.model.request;

/**
 * 关注/取消关注 事件
 *
 * @author wanghao
 * @create 2017-10-15 12:40
 */
public class SubscribeEvent extends EventBase {
    private static final long serialVersionUID = -3869232462817033163L;
    private String eventKey;
    private String ticket;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
