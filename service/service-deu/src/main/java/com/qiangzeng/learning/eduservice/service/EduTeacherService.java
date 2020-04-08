package com.qiangzeng.learning.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * @author qaingzeng
 * @since 2020-04-03
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //1 分页查询讲师的方法
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);

}
