package com.lee.nltx.service.impl;

import com.lee.nltx.mapper.PostingMapper;
import com.lee.nltx.mapper.UserMapper;
import com.lee.nltx.param.Result;
import com.lee.nltx.service.PostingService;
import com.lee.nltx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostingServiceImpl implements PostingService {
    @Autowired
    private PostingMapper postingMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result insertPosting(Map<String, Object> posting) {
        postingMapper.insertPosting(posting);
        return Result.success("添加成功");
    }


    @Override
    public Result listPostingByPage(Map<String, Object> page) {
        List<Map<String, Object>> postings = postingMapper.listPostingByPage(page);
        // 也可以通过数据库的多表查询完成
        for (Map<String, Object> posting : postings) {
            Long userId = (Long) posting.get("user_id");
            String username = userMapper.getUsernameByUserId(userId);
            posting.put("username", username);
            System.out.println(posting);
        }
        return Result.success(postings);
    }

    @Override
    public Result listPostingByTokenAndUserId(Map<String, Object> posting) {
        Long userId = userMapper.getUserIdByToken(posting.get("token").toString());
        posting.put("userId", userId);
        List<Map<String, Object>> ansPostings = postingMapper.listPostingByTokenAndUserId(posting);
        return Result.success(ansPostings);
    }

    @Override
    public Result deletePostingByPostingId(Long postingId) {
        postingMapper.deletePostingByPostingId(postingId);
        return Result.success("删除成功！");
    }
}
