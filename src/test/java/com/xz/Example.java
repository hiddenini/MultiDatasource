package com.xz;

import com.alibaba.fastjson.JSON;
import com.xz.mapper.UserMapper;
import com.xz.service.UserService;
import com.xz.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author xz
 * @date 2020/4/24 16:20
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest()
public class Example {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void master() {

        log.info("user:{}", JSON.toJSONString(userMapper.selectById(2l)));
        PlatformTransactionManager bean = SpringContextUtil.getBean(PlatformTransactionManager.class);
        log.info("bean======{}",bean);
    }

    @Test
    public void slave1() {
        log.info("user:{}", JSON.toJSONString(userMapper.selectAll()));
    }

    @Test
    public void slave2() {
        log.info("user:{}", JSON.toJSONString(userMapper.selectAllSlave2()));
    }

    @Test
    public void transSlave1() {
        userService.testTransSlave1();
    }

    @Test
    public void transSlave2() {
        PlatformTransactionManager bean = SpringContextUtil.getBean(PlatformTransactionManager.class);
        log.info("bean======{}",bean);
        userService.testTransSlave2();
    }
}
