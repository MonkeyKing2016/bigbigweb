package com.mk.diy.bigbigweb.model.request;

/**
 * 文本消息
 *
 * @author wanghao
 * @create 2017-10-15 12:23
 */
public class TextMsg extends MsgBase{
    private static final long serialVersionUID = 9186851328899486114L;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
