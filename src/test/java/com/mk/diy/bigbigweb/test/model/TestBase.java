package com.mk.diy.bigbigweb.test.model;

import java.io.Serializable;

/**
 * @author wanghao
 * @create 2017-10-15 11:56
 */
public class TestBase implements Serializable {
    private static final long serialVersionUID = 4291110785512934628L;
    protected String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
