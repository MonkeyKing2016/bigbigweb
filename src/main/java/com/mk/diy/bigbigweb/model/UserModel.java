package com.mk.diy.bigbigweb.model;


/**
 * @author wanghao
 * @create 2017-10-14 11:54
 */
public class UserModel extends Base {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserModel() {
    }

    public UserModel(String key, String id, String name) {
        super.key = key;
        this.id = id;
        this.name = name;
    }


}
