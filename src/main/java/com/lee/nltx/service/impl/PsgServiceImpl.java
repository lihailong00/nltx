package com.lee.nltx.service.impl;

import com.lee.nltx.mapper.PsgMapper;
import com.lee.nltx.param.ConstParam;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.PsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PsgServiceImpl implements PsgService {
    @Autowired
    private PsgMapper psgMapper;

    @Override
    public Result listPassageByPage(int currentPage, int pageSize) {
        return Result.success(psgMapper.listPassageByPage(currentPage, pageSize));
    }

    @Override
    public Result insertPassage(Map<String, Object> passage) {
        passage.put("create_time", new Date());
        passage.put("user_id", 9999);
        passage.put("view_count", 0);
        psgMapper.insertPassage(
                passage.get("title"),
                passage.get("description"),
                passage.get("content"),
                passage.get("create_time"),
                passage.get("user_id"),
                passage.get("view_count"));
        return Result.success("文章创建成功");
    }

    @Override
    public Result getPassageById(Long passageId) {
        Map<String, Object> passage = new HashMap<>();
        passage = psgMapper.getPassageById(passageId);
        if (passage == null) {
            return Result.fail("未查找到文章");
        } else {
            return Result.success(passage);
        }
    }
}
