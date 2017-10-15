package com.mk.diy.bigbigweb.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * base config
 *
 * @author wanghao
 * @create 2017-10-14 11:58
 */
@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
        (locations = "classpath:spring/applicationContext.xml")
public class TestBaseConfig extends AbstractJUnit4SpringContextTests{

}
