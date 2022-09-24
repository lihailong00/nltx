package com.lee.nltx.controller;

import com.lee.nltx.param.Result;
import com.lee.nltx.service.HomeMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/homemsg")
public class HomeMsgController {
    @Autowired
    private HomeMsgService homeMsgService;

    /**
     * 接收值
     * header: {
     *     "authorization": "your token without Bearer",
     * }
     * body: {
     *     "userId": Long类型的值
     * }
     * 有一个bug，建议采用openid验证
     */
    @PostMapping("/getmsgbyuserid")
    public Result getMsgByUserId(@RequestBody Map<String, Object> user) {
        return homeMsgService.getMsgByUserId(Long.parseLong(user.get("userId").toString()));
    }

    /**
     * 接收值
     * header: {
     *     "authorization": "your token without Bearer",
     * }
     * body: {
     *     homeMsgId: Long 类型
     * }
     */
    @PostMapping("/deletehomemsg")
    public Result deleteHomeMsgById(@RequestBody Map<String, Object> homeMsginfo) {
        return homeMsgService.deleteHomeMsgById(Long.parseLong(homeMsginfo.get("homeMsgId").toString()));
    }
}
