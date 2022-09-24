package com.lee.nltx.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PsgMapper {
    List<Map<String, Object>> listPassageByPage(@Param("currentPage") int currentPage,
                                                @Param("pageSize") int pageSize);


    void insertPassage(
            @Param("title") Object title,
            @Param("description") Object description,
            @Param("content") Object content,
            @Param("create_time") Object create_time,
            @Param("user_id") Object user_id,
            @Param("view_count") Object view_count);

    Map<String, Object> getPassageById(@Param("passageId") Long passageId);
}
