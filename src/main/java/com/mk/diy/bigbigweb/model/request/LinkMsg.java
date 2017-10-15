package com.mk.diy.bigbigweb.model.request;

/**
 * 链接消息
 *
 * @author wanghao
 * @create 2017-10-15 12:38
 */
public class LinkMsg extends MsgBase{
    private static final long serialVersionUID = -3582456902920045475L;
    private String title;
    private String description;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
