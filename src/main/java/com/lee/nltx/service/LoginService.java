package com.lee.nltx.service;

import com.lee.nltx.param.Result;
import org.springframework.stereotype.Service;

import java.util.Map;


public interface LoginService {
    Result login(Map<String, Object> user);

    void registry(String openId);

}
