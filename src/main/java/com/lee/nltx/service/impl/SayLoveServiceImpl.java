package com.lee.nltx.service.impl;

import com.lee.nltx.mapper.SayLoveMapper;
import com.lee.nltx.mapper.UserMapper;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.SayLoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SayLoveServiceImpl implements SayLoveService {
    @Autowired
    private SayLoveMapper sayLoveMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result listSayLoveByPage(Map<String, Object> page) {
        int currentPage = (int) page.get("currentPage");
        int pageSize = (int) page.get("pageSize");
        return Result.success(sayLoveMapper.listSayLoveByPage(currentPage, pageSize));
    }

    /**
     * 接收值：
     * {
     *     "token": "your token"
     * }
     * @param list
     * @return
     */
    @Override
    public Result listSayLoveByTokenAndUserId(Map<String, Object> list) {
        String token = list.get("token").toString();
        Long userId = userMapper.getUserIdByToken(token);
        list.put("userId", userId);
        return Result.success(sayLoveMapper.listSayLoveByTokenAndUserId(list));
    }

    @Override
    public Result insertSayLove(Map<String, Object> list) {
        sayLoveMapper.insertSayLove(list);
        return Result.success("成功发布表白墙！");
    }

    @Override
    public Result deleteSayLoveBySayLoveId(Long sayLoveId) {
        sayLoveMapper.deleteSayLoveBySayLoveId(sayLoveId);
        return Result.success("成功删除表白墙帖子！");
    }
}
