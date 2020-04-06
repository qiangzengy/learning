package com.qiangzeng.learning.eduservice.service;

import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;

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

}
