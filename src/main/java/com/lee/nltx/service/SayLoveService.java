package com.lee.nltx.service;


import com.lee.nltx.param.Result;

import java.util.List;
import java.util.Map;

public interface SayLoveService {
    Result listSayLoveByPage(Map<String, Object> page);

    Result listSayLoveByTokenAndUserId(Map<String, Object> list);

    Result insertSayLove(Map<String, Object> list);

    Result deleteSayLoveBySayLoveId(Long sayLoveId);
}
