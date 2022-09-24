package com.lee.nltx.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface HomeMsgMapper {
    @Select("select * from nltx_home_msg where user_id = #{userId} order by create_time desc")
    List<Map<String, Object>> getMsgByUserId(Long userId);

    @Insert("insert into nltx_home_msg (user_id, content, create_time) " +
            "value (#{userId}, #{content}, #{createTime})")
    void insertHomeMsg(Map<String, Object> msg);

    @Delete("delete from nltx_home_msg where home_msg_id = #{homeMsgId}")
    public void deleteHomeMsgById(Long homeMsgId);
}
