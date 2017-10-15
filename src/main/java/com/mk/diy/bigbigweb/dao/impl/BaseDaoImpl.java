package com.mk.diy.bigbigweb.dao.impl;

import com.mk.diy.bigbigweb.constant.RedisKeyConstant;
import com.mk.diy.bigbigweb.model.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * @author wanghao
 * @create 2017-10-14 12:50
 */
public abstract class BaseDaoImpl<T extends Base>  {
    Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    @Autowired
    protected RedisTemplate<String, T> redisTemplate;

    public void saveUser(T user) {
        ValueOperations<String, T> valueOper = redisTemplate.opsForValue();
        valueOper.set(RedisKeyConstant.USER_KEY+user.getKey(), user);
    }

    public T getUser(String key) {
        ValueOperations<String, T> valueOper = redisTemplate.opsForValue();
        return valueOper.get(RedisKeyConstant.USER_KEY+key);
    }

    public Integer deleteUser(String key) {
        key += RedisKeyConstant.USER_KEY;
        Integer result = 0;
        try {
            redisTemplate.delete(key);
            result = 1;
        } catch (Exception e){
            logger.error("error :"+e.getMessage());
        }
        return result;
    }
}
