package com.qiangzeng.learning.staservice.client;

import com.qiangzeng.learning.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //查询某一天注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    public ResponseResult countRegister(@PathVariable("day") String day);
}
