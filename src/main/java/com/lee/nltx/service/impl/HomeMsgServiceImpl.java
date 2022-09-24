package com.lee.nltx.service.impl;

import com.lee.nltx.mapper.HomeMsgMapper;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.HomeMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class HomeMsgServiceImpl implements HomeMsgService {
    @Autowired
    private HomeMsgMapper homeMsgMapper;
    @Override
    public Result getMsgByUserId(Long userId) {
        List<Map<String, Object>> msgList = homeMsgMapper.getMsgByUserId(userId);
        return Result.success(msgList);
    }

    @Override
    public Result deleteHomeMsgById(Long homeMsgId) {
        homeMsgMapper.deleteHomeMsgById(homeMsgId);
        return Result.success("成功删除个人消息");
    }
}
