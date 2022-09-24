package com.lee.nltx.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSON;
import com.lee.nltx.mapper.LoginMapper;
import com.lee.nltx.mapper.UserMapper;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.LoginService;
import com.lee.nltx.service.UserService;
import com.lee.nltx.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @author lhl
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;
    @Autowired
    private UserService userService;

    @Value("${weixin.appid}")
    private String appid;
    @Value("${weixin.secret}")
    private String secret;

    @Override
    public Result login(Map<String, Object> info) {
        String code = info.get("js_code").toString();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
        String replaceUrl = url.replace("{0}", appid).replace("{1}", secret).replace("{2}", code);
        Map<String, Object> res = JSON.parseObject(HttpUtil.get(replaceUrl), HashMap.class);
        if (res == null) {
            // 几乎不会出现这种错误
            return Result.fail("无法从微信服务器获取返回信息");
        }
        String openId = res.get("openid").toString();
        boolean exist = openIdExist(openId);
        // 该用户暂未注册，需要立即注册
        if (!exist) {
            registry(openId);
        }
        Map<String, Object> userInfo = getUserInfoByOpenId(openId);
        /**
         * token中需存放user_id和user_name
         */
        String token = JWTUtil.createToken(userInfo, 1000 * 60 * 60 * 24L);

        // 更新nltx_user表中的token
        userService.insertToken(openId, token);
        return Result.success(token);
    }

    public boolean openIdExist(String openId) {
        Map<String, Object> exist = loginMapper.openIdExist(openId);
        if (exist == null) {
            return false;
        } else {
            return true;
        }
    }

    public Map<String, Object> getUserInfoByOpenId(String openId) {
        return loginMapper.getUserInfoByOpenId(openId);
    }

    public String getRandomUsername() {
        Random random = new Random();
        int num = random.nextInt(100000000);
        String username = "南理" + String.valueOf(num);
        return username;
    }

    public String getRandomPassword() {
        String password = UUID.randomUUID().toString().replaceAll("-", "");
        return password;
    }

    @Override
    public void registry(String openId) {
        String username = getRandomUsername();
        while (loginMapper.usernameExist(username) != null) {
            username = getRandomUsername();
        }
        String password = getRandomPassword();
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("password", password);
        user.put("openId", openId);
        loginMapper.registry(user);
    }

}
