package com.qiangzeng.learning.eduservice.controller;

import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduCourseDescription;
import com.qiangzeng.learning.eduservice.service.EduCourseDescriptionService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiangzeng
 * @date 2020/4/6 下午9:39
 */

@RestController
@RequestMapping("/eduservice/edu-courseDescription")
public class EduCourseDescriptionController {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @ApiModelProperty(value = "获取课程描述信息")
    @RequestMapping(value = "getById/{id}",method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable String id){

        EduCourseDescription eduCourseDescription= eduCourseDescriptionService.getById(id);
        return ResponseResult.success(eduCourseDescription);

    }



}
