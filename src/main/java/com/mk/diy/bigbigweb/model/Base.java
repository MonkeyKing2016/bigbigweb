package com.mk.diy.bigbigweb.model;

import java.io.Serializable;

/**
 * BaseModel
 *
 * @author wanghao
 * @create 2017-10-14 12:47
 */
public class Base implements Serializable{
    private static final long serialVersionUID = -1624535692586100979L;

    protected String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
