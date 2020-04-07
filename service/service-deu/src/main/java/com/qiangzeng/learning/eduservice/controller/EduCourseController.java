package com.qiangzeng.learning.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.qiangzeng.learning.eduservice.entity.EduCourseDescription;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.entity.vo.CoursePublish;
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

    @ApiModelProperty(value = "添加课程信息")
    @RequestMapping(value = "createCourse",method = RequestMethod.POST)
    public ResponseResult createCourse(@RequestBody CourseInfo courseInfo){

        eduCourseService.createCourse(courseInfo);

        return ResponseResult.success();

    }

    //根据课程id查询课程基本信息
    @GetMapping("getCourseInfo/{courseId}")
    public ResponseResult getCourseInfo(@PathVariable String courseId) {
        CourseInfo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return ResponseResult.success(courseInfoVo);
    }



    //根据课程id查询课程确认信息
    @GetMapping("getPublishCourseInfo/{id}")
    public ResponseResult getPublishCourseInfo(@PathVariable String id) {
        CoursePublish coursePublishVo = eduCourseService.publishCourseInfo(id);
        return ResponseResult.success(coursePublishVo);
    }


    @ApiModelProperty(value = "修改课程信息")
    @RequestMapping(value = "updateCourse",method = RequestMethod.POST)
    public ResponseResult updateCourse(@RequestBody CourseInfo courseInfo){
        eduCourseService.updateCourse(courseInfo);
        return ResponseResult.success();

    }


    @ApiModelProperty(value = "删除课程信息")
    @RequestMapping(value = "{courseId}",method = RequestMethod.DELETE)
    public ResponseResult deleteCourse(@PathVariable String courseId){
        //删除课程
        eduCourseService.removeCourse(courseId);
        return ResponseResult.success();

    }


    @ApiModelProperty(value = "获取课程信息")
    @RequestMapping(value = "findAllCourse/{c}/{size}",method = RequestMethod.GET)
    public ResponseResult findAllCourse(@PathVariable long c,@PathVariable long size){
        Page<EduCourse> page=new Page<>(c,size);
        IPage<EduCourse> iPage=eduCourseService.page(page,null);
        return ResponseResult.success(iPage);

    }

    @ApiModelProperty(value = "修改课程状态(发布)")
    @RequestMapping(value = "publishCourse/{id}",method = RequestMethod.POST)
    public ResponseResult publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");//设置课程发布状态
        eduCourseService.updateById(eduCourse);
        return ResponseResult.success();
    }


}

