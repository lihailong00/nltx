package com.lee.nltx.mapper;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {

    // 用户添加商品
    void insertGoods(Map<String, Object> goods);

    // 查找【卖家发布】商品
    List<Map<String, Object>> listGoodsByPage0(Map<String, Object> page);

    // 查找【买家悬赏】商品
    List<Map<String, Object>> listGoodsByPage1(Map<String, Object> page);

    int goodsIdCount(@Param(value = "goodsId") Long goodsId);

    void deleteGoodsById(@Param(value = "goodsId") Long goodId);

    Map<String, Object> getGoodsAllInfoByGoodsId(@Param(value = "goodsId") Long goodsId);

    List<Map<String, Object>> listGoodsByUserId(Long userId);

    void deleteGoodsByGoodsId(Long goodsId);
}
