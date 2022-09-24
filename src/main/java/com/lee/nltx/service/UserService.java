package com.lee.nltx.service;

import com.lee.nltx.param.Result;

import java.util.List;
import java.util.Map;

public interface UserService {
    int insertToken(String openId, String token);

    Long getUserIdByToken(String token);

    String getUsernameByUserId(Long userId);

    Result getUserAllInfoByToken(String token);

    Result setUserAllInfoByToken(Map<String, Object> userInfo);

    int getUsernameCount(String username);


    Result getUserIdAndNameByToken(String token);
}
