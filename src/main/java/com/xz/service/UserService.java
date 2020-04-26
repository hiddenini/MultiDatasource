package com.xz.service;

import com.xz.entity.User;
import com.xz.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xz
 * @date 2020/4/26 15:27
 **/
@Slf4j
@Service
public class UserService  {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void testTransSlave1(){
        User user=new User();
        user.setUsername("slave1-zjw");
        user.setAge(25);
        user.setPassword("bonatgy");
        userMapper.save(user);
        int i=1/0;
    }

    @Transactional
    public void testTransSlave2(){
        userMapper.saveSlave2();
        int i=1/0;
    }
    
}
