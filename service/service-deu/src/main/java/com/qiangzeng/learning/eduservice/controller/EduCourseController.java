package com.qiangzeng.learning.eduservice.controller;


import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-06
 */
@RestController
@RequestMapping("/eduservice/edu-course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiModelProperty(value = "添加课程信息")
    @RequestMapping(value = "createCourse",method = RequestMethod.POST)
    public ResponseResult createCourse(@RequestBody CourseInfo courseInfo){

        eduCourseService.createCourse(courseInfo);

        return ResponseResult.success();

    }

}

