package com.lee.nltx.service.impl;

import com.lee.nltx.mapper.GoodsMapper;
import com.lee.nltx.mapper.GoodsRecordMapper;
import com.lee.nltx.mapper.HomeMsgMapper;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsRecordMapper goodsRecordMapper;

    @Autowired
    private HomeMsgMapper homeMsgMapper;

    @Override
    public Result insertGoods(Map<String, Object> goods) {
        goodsMapper.insertGoods(goods);
        return Result.success("商品发布成功！");
    }


    @Override
    public Result listGoodsByPage(Map<String, Object> page) {
        if ((int)page.get("goodsType") == 0) {
            return Result.success(goodsMapper.listGoodsByPage0(page));
        } else {
            return Result.success(goodsMapper.listGoodsByPage1(page));
        }
    }


    @Override
    public int goodsIdCount(Long goodsId) {
        return goodsMapper.goodsIdCount(goodsId);
    }


    @Override
    public Result handleGoods(Map<String, Object> order) {
        Long goodId = Long.valueOf(order.get("goodsId").toString());
        Long receiverId = Long.valueOf(order.get("receiverId").toString());
        // 先判断nltx_goods表中是否有goodId这个商品
        if (goodsIdCount(goodId) == 0) {
            return Result.fail("商品已卖完啦！");
        }

        // 再从数据库中获取商品的所有信息
        Map<String, Object> goodsInfo = goodsMapper.getGoodsAllInfoByGoodsId(goodId);
        // 再从数据库中删除商品
        goodsMapper.deleteGoodsById(goodId);

        // 再向nltx_goods_record数据库中插入一条交易成功消息
        Map<String, Object> record = new HashMap<>();
        record.put("senderId", order.get("senderId"));
        record.put("receiverId", order.get("receiverId"));
        record.put("currentPrice", goodsInfo.get("current_price"));
        record.put("originalPrice", goodsInfo.get("original_price"));
        record.put("goodsName", goodsInfo.get("goods_name"));
        record.put("goodsDescription", goodsInfo.get("description"));
        record.put("createTime", new Date());

        goodsRecordMapper.insertRecord(record);

        // 最后向nltx_home_msg添加1条信息。
        // 消息属于发起方
        Map<String, Object> homeMsg = new HashMap<>();
        homeMsg.put("userId", order.get("senderId"));
        homeMsg.put("content", "您已拍下商品：" +
                goodsInfo.get("name") +
                "。对方的联系方式是：" + goodsInfo.get("contact"));
        homeMsg.put("createTime", new Date());
        homeMsgMapper.insertHomeMsg(homeMsg);

        return Result.success("购买成功！");
    }

    @Override
    public Result listGoodsByUserId(Long userId) {
        List<Map<String, Object>> goods = goodsMapper.listGoodsByUserId(userId);
        return Result.success(goods);
    }

    @Override
    public Result deleteGoodsByGoodsId(Long goodsId) {
        goodsMapper.deleteGoodsByGoodsId(goodsId);
        return Result.success("删除成功！");
    }
}
