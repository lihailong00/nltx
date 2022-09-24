package com.lee.nltx.service;

import com.lee.nltx.param.Result;

import java.util.Map;

public interface PsgService {
    Result listPassageByPage(int currentPage, int pageSize);

    Result insertPassage(Map<String, Object> passage);

    Result getPassageById(Long passageId);
}
