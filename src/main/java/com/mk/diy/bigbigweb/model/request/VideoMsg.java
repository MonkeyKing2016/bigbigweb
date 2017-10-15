package com.mk.diy.bigbigweb.model.request;

/**
 * 小视频消息/视频消息
 *
 * @author wanghao
 * @create 2017-10-15 12:28
 */
public class VideoMsg extends MsgBase {
    private static final long serialVersionUID = 1621199112769439763L;
    private String thumbMediaId;
    private String mediaId;

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

}
