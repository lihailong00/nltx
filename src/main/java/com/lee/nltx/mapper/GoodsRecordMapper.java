package com.lee.nltx.mapper;

import org.apache.ibatis.annotations.Insert;

import java.util.Map;

public interface GoodsRecordMapper {
    void insertRecord(Map<String, Object> record);
}
