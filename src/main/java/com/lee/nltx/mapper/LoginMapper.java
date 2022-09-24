package com.lee.nltx.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

public interface LoginMapper {
    @Select("select * from nltx_user where username = #{username} and password = #{password}")
    Map<String, Object> login(Map<String, Object> user);

    @Select("select user_id from nltx_user where open_id = #{openId}")
    Map<String, Object> openIdExist(String openId);

    @Insert("insert into nltx_user (username, password, open_id) values (#{username}, #{password}, #{openId})")
    void registry(Map<String, Object> user);

    @Select("select username from nltx_user where username = #{username}")
    Map<String, Object> usernameExist(String username);

    @Select("select username, user_id from nltx_user where open_id = #{openId}")
    Map<String, Object> getUserInfoByOpenId(String openId);
}
