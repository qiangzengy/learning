package com.qiangzeng.learning.eduservice.controller;


import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduCourseDescription;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.service.EduCourseDescriptionService;
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

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @ApiModelProperty(value = "添加课程信息")
    @RequestMapping(value = "createCourse",method = RequestMethod.POST)
    public ResponseResult createCourse(@RequestBody CourseInfo courseInfo){

        eduCourseService.createCourse(courseInfo);

        return ResponseResult.success();

    }


    @ApiModelProperty(value = "修改课程信息")
    @RequestMapping(value = "updateCourse",method = RequestMethod.POST)
    public ResponseResult updateCourse(@RequestBody CourseInfo courseInfo){
        eduCourseService.updateCourse(courseInfo);
        return ResponseResult.success();

    }


    @ApiModelProperty(value = "删除课程信息")
    @RequestMapping(value = "deleteCourse",method = RequestMethod.DELETE)
    public ResponseResult deleteCourse(@RequestBody CourseInfo courseInfo){
        eduCourseService.removeById(courseInfo.getId());
        eduCourseDescriptionService.deteleCourseDescription(courseInfo.getId());
        return ResponseResult.success();

    }

}

