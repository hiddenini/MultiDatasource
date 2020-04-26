package com.xz.mapper;


import com.xz.anno.DS;
import com.xz.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: yukong
 * @Date: 2018/8/13 19:47
 * @Description: UserMapper接口
 */

public interface UserMapper {

    /**
     * 新增用户
     * @param user
     * @return
     */
    @DS("slave1")
    int save(User user);

    @DS("slave2")
    @Insert("INSERT INTO `user` ( `age`, `password`, `sex`, `username`) VALUES ( '26', 'zjw1', '2', 'slave2-tgy1')")
    int saveSlave2();

    /**
     * 更新用户信息
     * @param user
     * @return
     */

    int update(User user);

    /**
     * 根据id删除
     * @param id
     * @return
     */

    int deleteById(Long id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @DS
    User selectById(Long id);

    /**
     * 查询所有用户信息
     * @return
     */
    @DS("slave1")
    List<User> selectAll();

    /**
     * 查询所有用户信息
     * @return
     */
    @DS("slave2")
    @Select("select * from user")
    List<User> selectAllSlave2();
}
