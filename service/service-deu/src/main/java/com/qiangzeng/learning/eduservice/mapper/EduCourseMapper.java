package com.qiangzeng.learning.eduservice.mapper;

import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qiangzeng.learning.eduservice.entity.frontvo.CourseWeb;
import com.qiangzeng.learning.eduservice.entity.vo.CoursePublish;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-04-06
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

     CoursePublish getPublishCourseInfo(String courseId);

    CourseWeb getBaseCourseInfo(String courseId);



}
