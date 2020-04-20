package com.qiangzeng.learning.staservice.controller;


import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/staservice/statistics-daily")
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService staService;

    //统计某一天注册人数,生成统计数据
    @PostMapping("registerCount/{day}")
    public ResponseResult registerCount(@PathVariable String day) {
        staService.registerCount(day);
        return ResponseResult.success();
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public ResponseResult showData(@PathVariable String type,@PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = staService.getShowData(type,begin,end);
        return ResponseResult.success(map);
    }

}

