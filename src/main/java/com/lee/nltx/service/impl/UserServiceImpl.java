package com.lee.nltx.service.impl;

import com.lee.nltx.mapper.UserMapper;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int insertToken(String openId, String token) {
        return userMapper.insertToken(openId, token);
    }

    @Override
    public Long getUserIdByToken(String token) {
        return userMapper.getUserIdByToken(token);
    }

    @Override
    public String getUsernameByUserId(Long userId) {
        return userMapper.getUsernameByUserId(userId);
    }

    @Override
    public Result getUserAllInfoByToken(String token) {
        Map<String, Object> userInfo = userMapper.getUserAllInfoByToken(token);
        return Result.success(userInfo);
    }

    @Override
    public int getUsernameCount(String username) {
        return userMapper.getUsernameCount(username);
    }

    @Override
    public Result setUserAllInfoByToken(Map<String, Object> userInfo) {
        String username = userInfo.get("username").toString();
        if (getUsernameCount(username) == 1) {
            return Result.fail("用户名已存在！");
        } else {
            userMapper.setUserAllInfoByToken(userInfo);
            return Result.success("添加成功");
        }
    }

    @Override
    public Result getUserIdAndNameByToken(String token) {
        return Result.success(userMapper.getUserIdAndNameByToken(token));
    }
}
