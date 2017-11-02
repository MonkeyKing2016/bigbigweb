package com.mk.diy.bigbigweb.test.model;

/**
 * @author wanghao
 * @create 2017-10-27 14:02
 */
public class TestSort {
    public TestSort() {
    }

    public TestSort(int sort, String name) {
        this.sort = sort;
        this.name = name;
    }

    private int sort;
    private String name;

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
