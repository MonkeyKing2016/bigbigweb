package com.mk.diy.bigbigweb.test.model;

import java.io.Serializable;

/**
 * @author wanghao
 * @create 2017-10-15 11:56
 */
public class TestCat extends TestBase implements Serializable{
    private static final long serialVersionUID = -7600048726378016355L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
