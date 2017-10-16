package com.mk.diy.bigbigweb.model.response;

import com.mk.diy.bigbigweb.converter.CDATAConvert;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

/**
 * @author wanghao
 * @create 2017-10-15 20:26
 */
@XStreamAlias("xml")
public class TextResponse extends ResponseBase{
    private static final long serialVersionUID = -3019293689932660162L;

    @XStreamAlias("Content")
    @XStreamConverter(CDATAConvert.class)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
