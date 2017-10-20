package com.mk.diy.bigbigweb.model.request;

import java.io.Serializable;

/**
 * 客服接口model
 *
 * @author wanghao
 * @create 2017-10-19 9:35
 */
public class CustomSendMsg implements Serializable{
    private static final long serialVersionUID = -8459675812682630740L;
    public CustomSendMsg() {
    }

    public CustomSendMsg(String touser, String msgtype) {
        this.touser = touser;
        this.msgtype = msgtype;
    }

    private String touser;
    private String msgtype;
    private CustomSendText text;
    private CustomSendImage image;
    private CustomSendVoice voice;
    private CustomSendVideo video;
    private CustomSendMusic music;
    private CustomSendNews news;
    private CustomSendMpnews mpnews;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public CustomSendImage getImage() {
        return image;
    }

    public void setImage(CustomSendImage image) {
        this.image = image;
    }

    public CustomSendVoice getVoice() {
        return voice;
    }

    public void setVoice(CustomSendVoice voice) {
        this.voice = voice;
    }

    public CustomSendVideo getVideo() {
        return video;
    }

    public void setVideo(CustomSendVideo video) {
        this.video = video;
    }

    public CustomSendMusic getMusic() {
        return music;
    }

    public void setMusic(CustomSendMusic music) {
        this.music = music;
    }

    public CustomSendNews getNews() {
        return news;
    }

    public void setNews(CustomSendNews news) {
        this.news = news;
    }

    public CustomSendMpnews getMpnews() {
        return mpnews;
    }

    public void setMpnews(CustomSendMpnews mpnews) {
        this.mpnews = mpnews;
    }

    public CustomSendText getText() {
        return text;
    }

    public void setText(CustomSendText text) {
        this.text = text;
    }
}
