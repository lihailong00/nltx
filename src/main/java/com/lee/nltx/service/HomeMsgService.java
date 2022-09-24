package com.lee.nltx.service;

import com.lee.nltx.param.Result;

import java.util.List;
import java.util.Map;

public interface HomeMsgService {
    Result getMsgByUserId(Long userId);

    Result deleteHomeMsgById(Long homeMsgId);
}
