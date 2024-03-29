package com.example.demo.Dao;


import com.example.demo.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 描述：用户DAO
 * @author Ay
 * @date   2017/11/20.
 */
@Mapper
public interface AyUserDao {

    /**
     *  描述：通过用户名和密码查询用户
     * @param name
     * @param password
     */
    AyUser findByNameAndPassword(@Param("name") String name, @Param("password") String password);

    /**
     *  描述：通过用户名查询用户
     * @param name
     */
    AyUser findByUserName(@Param("name") String name);

}
