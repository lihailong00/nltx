package com.lee.nltx.controller;

import com.lee.nltx.param.Result;
import com.lee.nltx.service.GoodsService;
import com.lee.nltx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private UserService userService;
    /**
     * 接收值：
     * header: {
     *     "authorization": "your token without Bearer",
     * }
     * body: {
     *     "name": "商品名称",
     *     "originalPrice": "商品原价,可能为空, int类型",
     *     "currentPrice": "商品现价, int类型",
     *     "contact": "发布者的联系方式",
     *     "description": "商品描述信息",
     *     "goodsType": "买家/卖家 只有这两种可能" 需要在controller中将 买家/卖家 转成 1/0
     * }
     * controller处理中，goods中还需添加userId和createTime
     */
    @PostMapping("/creategoods")
    public Result createGoods(@RequestBody Map<String, Object> goods,
                              @RequestHeader(value = "authorization") String token) {
        String goodsType = goods.get("goodsType").toString();
        if (goodsType.equals("卖家")) {
            goods.put("goodsType", 0);
        }
        if (goodsType.equals("买家")) {
            goods.put("goodsType", 1);
        }
        // 添加必要商品信息
        Long userId = userService.getUserIdByToken(token);
        goods.put("createTime", new Date());
        goods.put("userId", userId);
        goodsService.insertGoods(goods);

        return Result.success("商品发布成功！");
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token without Bearer",
     * }
     * body: {
     *     currentPage0: int类型,
     *     pageSize: int类型,
     *     keyword: "xxx",
     *     goodsType: int类型 0或1
     * }
     */
    @PostMapping("/listgoodsbypage0")
    public Result listGoodsByPage0(@RequestBody Map<String, Object> page) {
        return goodsService.listGoodsByPage(page);
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token without Bearer",
     * }
     * body: {
     *     currentPage1: int类型,
     *     pageSize: int类型,
     *     keyword: "xxx",
     *     goodsType: int类型 0或1 0表示卖家发布的商品，1表示买家悬赏的商品,
     *     sortType: int类型 0或1 0表示按照时间降序排序，1表示按照价格降序排序
     * }
     */
    @PostMapping("/listgoodsbypage1")
    public Result listGoodsByPage1(@RequestBody Map<String, Object> page) {
        System.out.println(page);
        return goodsService.listGoodsByPage(page);
    }

    /**
     * 接收值：
     * header: {
     *     "authorization": "your token without Bearer",
     * }
     * body: {
     *     "goodsId": Long类型数值, 商品编号
     *     "receiverId": Long类型 订单接收者id
     *
     *     controller中需要添加的部分
     *     senderId: Long类型 订单发起者id
     * }
     * 发起购买请求
     */
    @PostMapping("/handlegoods")
    public Result handleGoods(@RequestBody Map<String, Object> order,
                              @RequestHeader("authorization") String token) {
        Long senderId = userService.getUserIdByToken(token);
        order.put("senderId", senderId);
        return goodsService.handleGoods(order);
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token"
     * }
     * @param token
     * @return
     */
    @PostMapping("/listgoodsbyuserid")
    public Result listGoodsByUserId(@RequestHeader(value = "authorization") String token) {
        Long userId = userService.getUserIdByToken(token);
        return goodsService.listGoodsByUserId(userId);
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token"
     * }
     * body: {
     *     "goodsId": Long 类型
     * }
     * @param info
     * @return
     */
    @PostMapping("/deletegoodsbygoodsid")
    public Result deleteGoodsByGoodsId(@RequestBody Map<String, Object> info) {
        Long goodsId = Long.parseLong(info.get("goodsId").toString());
        return goodsService.deleteGoodsByGoodsId(goodsId);
    }
}
