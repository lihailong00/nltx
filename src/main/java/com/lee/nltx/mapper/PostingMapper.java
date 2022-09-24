package com.lee.nltx.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface PostingMapper {

    @Insert("insert into nltx_posting (user_id, content, title, create_time) " +
            "value (#{userId}, #{content}, #{title}, #{createTime})")
    int insertPosting(Map<String, Object> posting);

    @Select("select * from nltx_posting where title like CONCAT('%',#{keyword},'%') order by create_time desc " +
            "limit ${(currentPage - 1) * pageSize}, #{pageSize}")
    List<Map<String, Object>> listPostingByPage(Map<String, Object> page);

    @Select("select * from nltx_posting where user_id = #{userId} order by create_time desc")
    List<Map<String, Object>> listPostingByTokenAndUserId(Map<String, Object> posting);

    @Delete("delete from nltx_posting where posting_id = #{postingId}")
    void deletePostingByPostingId(Long postingId);
}
