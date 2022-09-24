package com.lee.nltx.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.lee.nltx.mapper.LoginMapper;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.LoginService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    /**
     * 接收值:
     * body {
     *     "js_code": "xxx"
     * }
     * 返回值:
     * 成功(返回自定义Result类型)
     * {
     *     "success": true,
     *     "code": 200,
     *     "msg": "success",
     *     "data": "token"
     * }
     * 失败(返回自定义Result类型)
     * {
     *     "success": false,
     *     "code": 250,
     *     "msg": "错误信息提示",
     *     "data": {}
     * }
     */
    @PostMapping("")
    public Result login(@RequestBody Map<String, Object> info) {
        return loginService.login(info);
    }
}

