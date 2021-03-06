package com.qiangzeng.learning.eduservice.service;

import com.qiangzeng.learning.eduservice.entity.EduCourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-06
 */
public interface EduCourseDescriptionService extends IService<EduCourseDescription> {

    void deteleCourseDescription(String id);

}
