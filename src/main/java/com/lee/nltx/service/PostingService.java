package com.lee.nltx.service;

import com.lee.nltx.param.Result;

import java.util.List;
import java.util.Map;

public interface PostingService {

    Result insertPosting(Map<String, Object> posting);

    Result listPostingByPage(Map<String, Object> page);

    Result listPostingByTokenAndUserId(Map<String, Object> posting);

    Result deletePostingByPostingId(Long postingId);
}
