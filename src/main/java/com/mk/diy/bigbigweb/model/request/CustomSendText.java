package com.mk.diy.bigbigweb.model.request;

import java.io.Serializable;

/**
 * 客服消息-文本信息model
 *
 * @author wanghao
 * @create 2017-10-19 9:36
 */
public class CustomSendText implements Serializable {
    private static final long serialVersionUID = -580706376605718698L;

    public CustomSendText() {
    }

    public CustomSendText(String content) {
        this.content = content;
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
