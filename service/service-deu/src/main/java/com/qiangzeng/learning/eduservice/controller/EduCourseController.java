package com.qiangzeng.learning.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.qiangzeng.learning.eduservice.entity.EduCourseDescription;
import com.qiangzeng.learning.eduservice.entity.EduTeacher;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.entity.vo.CoursePublish;
import com.qiangzeng.learning.eduservice.entity.vo.CourseQuery;
import com.qiangzeng.learning.eduservice.entity.vo.TeacherQuery;
import com.qiangzeng.learning.eduservice.service.EduCourseDescriptionService;
import com.qiangzeng.learning.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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

    @ApiModelProperty(value = "根据课程id查询课程基本信息")
    @RequestMapping(value = "getCourseInfo/{courseId}",method = RequestMethod.GET)
    public ResponseResult getCourseInfo(@PathVariable String courseId) {
        CourseInfo courseInfoVo = eduCourseService.getCourseInfo(courseId);
        return ResponseResult.success(courseInfoVo);
    }


    @ApiModelProperty(value = "根据课程id查询课程确认信息")
    @RequestMapping(value = "getPublishCourseInfo/{id}",method = RequestMethod.GET)
    public ResponseResult getPublishCourseInfo(@PathVariable String id) {
        CoursePublish coursePublish = eduCourseService.publishCourseInfo(id);
        return ResponseResult.success(coursePublish);
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


    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "findAllCourse/{curret}/{size}",method = RequestMethod.POST)
    public ResponseResult findAllCourse(@PathVariable long curret, @PathVariable long size, @RequestBody(required = false)CourseQuery courseQuery){
        Page<EduCourse> page=new Page<>(curret,size);
        QueryWrapper<EduCourse> queryWrapper=new QueryWrapper<>();
        String title=courseQuery.getTitle();
        String startTime=courseQuery.getStartTime();
        String endTime=courseQuery.getEndTime();
        if (!StringUtils.isEmpty(title)){
            queryWrapper.like("name",title);
        }
        if (!StringUtils.isEmpty(startTime)){
            queryWrapper.gt("gmt_create",startTime);
        }
        if (!StringUtils.isEmpty(endTime)){
            queryWrapper.gt("gmt_modified",endTime);
        }

        IPage<EduCourse> iPage=eduCourseService.page(page,queryWrapper);
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

