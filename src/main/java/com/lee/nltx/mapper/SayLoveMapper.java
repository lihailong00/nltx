package com.lee.nltx.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SayLoveMapper {

     @Select("select * from nltx_say_love order by create_time desc " +
             "limit ${(currentPage - 1) * pageSize}, #{pageSize}")
    List<Map<String, Object>> listSayLoveByPage(int currentPage, int pageSize);

    /**
     * 接收值：
     * {
     *     "token": "your token" mapper中查询并没有用到token
     *     "user_id": Long 类型
     * }
     */
    @Select("select * from nltx_say_love where user_id = #{userId} order by create_time desc")
    List<Map<String, Object>> listSayLoveByTokenAndUserId(Map<String, Object> list);

    @Insert("insert into nltx_say_love (title, content, user_id, create_time) " +
            "value (#{title}, #{content}, #{userId}, #{createTime})")
    void insertSayLove(Map<String, Object> list);

    @Delete("delete from nltx_say_love where say_love_id = #{sayLoveId} order by create_time desc")
    void deleteSayLoveBySayLoveId(Long sayLoveId);
}
