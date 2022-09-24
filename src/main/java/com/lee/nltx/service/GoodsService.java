package com.lee.nltx.service;

import com.lee.nltx.param.Result;

import java.util.Map;

public interface GoodsService {
    Result insertGoods(Map<String, Object> goods);

    Result listGoodsByPage(Map<String, Object> page);

    int goodsIdCount(Long goodsId);

    Result handleGoods(Map<String, Object> order);

    Result listGoodsByUserId(Long userId);

    Result deleteGoodsByGoodsId(Long goodsId);
}
