package com.lee.nltx.controller;

import com.lee.nltx.param.Result;
import com.lee.nltx.service.SayLoveService;
import com.lee.nltx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/saylove")
public class SayLoveController {
    @Autowired
    private SayLoveService sayLoveService;

    @Autowired
    private UserService userService;

    /**
     * 接收值
     * header {
     *     "authorization": "token"
     * }
     * body {
     *     "currentPage": int类型,
     *     "pageSize": int类型
     * }
     * 返回值
     * {
     *
     * }
     *
     */
    @PostMapping("/listsaylovebyapge")
    public Result listSayLoveByPage(@RequestBody Map<String, Object> page) {
        return sayLoveService.listSayLoveByPage(page);
    }

    /**
     * header: {
     *     "authorization": "your token"
     * }
     */
    @PostMapping("/listsaylovebytoken")
    public Result listSayLoveByTokenAndUserId(@RequestHeader(value = "authorization") String token) {
        Map<String, Object> mp = new HashMap<>();
        mp.put("token", token);
        return sayLoveService.listSayLoveByTokenAndUserId(mp);
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token"
     * }
     * body: {
     *     "title": "xxx",
     *     "content": "xxx"
     * }
     * controller处理中：还要添加 userId和createTime
     */
    @PostMapping("/insertsaylove")
    public Result insertSayLove(@RequestBody Map<String, Object> list,
                              @RequestHeader(value = "authorization") String token) {
        Long userId = userService.getUserIdByToken(token);
        list.put("createTime", new Date());
        list.put("userId", userId);
        return sayLoveService.insertSayLove(list);
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token"
     * }
     * body: {
     *     "sayLoveId": Long类型
     * }
     */
    @PostMapping("/deletesaylove")
    public Result deleteSayLoveBySayLoveId(@RequestBody Map<String, Object> info) {
        Long sayLoveId = Long.parseLong(info.get("sayLoveId").toString());
        return sayLoveService.deleteSayLoveBySayLoveId(sayLoveId);
    }
}
