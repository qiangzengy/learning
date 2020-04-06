package com.qiangzeng.learning.eduservice.service.impl;

import com.qiangzeng.learning.base.exceptionhandler.MyException;
import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.qiangzeng.learning.eduservice.entity.EduCourseDescription;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.mapper.EduCourseMapper;
import com.qiangzeng.learning.eduservice.service.EduCourseDescriptionService;
import com.qiangzeng.learning.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-06
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {


    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public void createCourse(CourseInfo courseInfo) {

        //添加课程信息
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int now=baseMapper.insert(eduCourse);
        if(now==0){
           throw  new MyException(20001,"添加失败");
        }

        //添加课程描述信息
        String cid=eduCourse.getId();
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setId(cid);
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);

    }
}
