package com.qiangzeng.learning.eduservice.controller;


import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduVideo;
import com.qiangzeng.learning.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-07
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @ApiModelProperty("创建小节")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseResult createVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return ResponseResult.success();

    }

}

