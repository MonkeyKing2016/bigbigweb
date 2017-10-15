package com.mk.diy.bigbigweb.dao;

import com.mk.diy.bigbigweb.model.Base;

/**
 * @author wanghao
 * @create 2017-10-14 12:50
 */
public interface IBaseDao<T extends Base> {
    void saveUser(T user);
    T getUser(String key);
    Integer deleteUser(String key);
}
