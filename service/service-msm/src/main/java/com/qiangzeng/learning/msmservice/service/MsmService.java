package com.qiangzeng.learning.msmservice.service;

import java.util.Map;

/**
 * @author qiangzeng
 * @date 2020/4/8 下午3:07
 */
public interface MsmService {

    //发送短信的方法
    boolean send(Map<String, Object> param, String phone);
}
