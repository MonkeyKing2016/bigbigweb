package com.mk.diy.bigbigweb.model.request;

/**
 * 语音消息
 *
 * @author wanghao
 * @create 2017-10-15 12:28
 */
public class VoiceMsg extends MsgBase {
    private static final long serialVersionUID = 486185605735317174L;
    private String format;
    private String mediaId;
    private String recognition;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }
}
