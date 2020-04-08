package com.qiangzeng.learning.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiangzeng.learning.eduservice.entity.frontvo.CourseFront;
import com.qiangzeng.learning.eduservice.entity.frontvo.CourseWeb;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.entity.vo.CoursePublish;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-06
 */
public interface EduCourseService extends IService<EduCourse> {

    void createCourse(CourseInfo courseInfo);
    void updateCourse(CourseInfo courseInfo);
    //根据课程id查询课程基本信息
    CourseInfo getCourseInfo(String courseId);
    void removeCourse(String courseId);
    CoursePublish publishCourseInfo(String id);
    //根据课程id，编写sql语句查询课程信息
    CourseWeb getBaseCourseInfo(String courseId);
    //根据讲师id查询课程列表
    List<EduCourse>getByTeacherId(String tedcherId);
    Map<String,Object> getCourseFrontList(Page page, CourseFront courseFront);

}
