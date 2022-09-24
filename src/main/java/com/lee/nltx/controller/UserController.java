package com.lee.nltx.controller;

import com.lee.nltx.param.Result;
import com.lee.nltx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 暂时没有用
     * 接收值:
     * header {
     *     "authorization": "token"
     * }
     *
     * 返回值:
     * 成功
     * {
     *     "success": true,
     *     "code": 200,
     *     "msg": "验证成功",
     *     "data": {
     *         "username": "xxx",
     *         "userId": "xxx"
     *     }
     * },
     * 失败(拦截器中)
     * {
     *     "success": false,
     *     "code": 250,
     *     "msg": "token"验证失败,
     *     data: {}
     * }
     */
    @PostMapping("/getpersonalinfo")
    public Result getUserIdAndNameByToken(@RequestHeader(value = "authorization") String token) {
        return userService.getUserIdAndNameByToken(token);
    }


    /**
     * 接收值:
     * header {
     *     "authorization": "token"
     * }
     *
     * 返回值:
     * 成功
     * {
     *     "success": true,
     *     "code": 200,
     *     "msg": "验证成功",
     *     "data": {
     *         "username": "xxx",
     *         "userId": "xxx"
     *     }
     * },
     * 失败(拦截器中)
     * {
     *     "success": false,
     *     "code": 250,
     *     "msg": "token"验证失败,
     *     data: {}
     * }
     */
    @PostMapping("/getuserallinfo")
    public Result getUserAllInfoByToken(@RequestHeader(value = "authorization") String token) {
        return userService.getUserAllInfoByToken(token);
    }

    /**
     * 接收值
     * header: {
     *     authorization: "your token"
     * }
     * body: {
     *     username: "xxx",
     *     realName: "xxx"
     * }
     * controller 中需要在body中添加 token
     * @param userInfo
     * @return
     */
    @PostMapping("/setuserinfo")
    public Result setUserAllInfoByToken(@RequestBody Map<String, Object> userInfo,
                                        @RequestHeader(value = "authorization") String token) {
        userInfo.put("token", token);
        return userService.setUserAllInfoByToken(userInfo);
    }
}
