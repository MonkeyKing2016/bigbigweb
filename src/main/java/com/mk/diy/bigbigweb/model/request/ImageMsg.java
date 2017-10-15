package com.mk.diy.bigbigweb.model.request;

/**
 * 图片消息
 *
 * @author wanghao
 * @create 2017-10-15 12:28
 */
public class ImageMsg extends MsgBase {
    private static final long serialVersionUID = 408693370504885210L;
    private String picUrl;
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
