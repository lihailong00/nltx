package com.lee.nltx.mapper;

import com.lee.nltx.param.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Update("update nltx_user set token = #{token} " +
            "where open_id = #{openId}")
    int insertToken(String openId, String token);

    @Select("select user_id from nltx_user where token = #{token}")
    Long getUserIdByToken(String token);

    @Select("select username from nltx_user where user_id = #{userId}")
    String getUsernameByUserId(Long userId);

    @Select("select username, user_id, real_name, email, phone_num, qq_num, wechat_num, student_id, school, dept, major, description, degree, birthday from nltx_user where token = #{token}")
    Map<String, Object> getUserAllInfoByToken(String token);

    @Select("select count(username) from nltx_user where username = #{username}")
    int getUsernameCount(String username);

    @Update("update nltx_user set username = #{username}, real_name = #{realName} where token = #{token}")
    void setUserAllInfoByToken(Map<String, Object> userInfo);

    // 淘汰
    @Select("select user_id, username from nltx_user where token = #{token}")
    List<Map<String, Object>> getUserIdAndNameByToken(String token);
}
