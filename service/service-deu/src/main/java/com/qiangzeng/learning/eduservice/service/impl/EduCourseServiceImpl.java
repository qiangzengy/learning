package com.qiangzeng.learning.eduservice.service.impl;

import com.qiangzeng.learning.base.exceptionhandler.MyException;
import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.qiangzeng.learning.eduservice.entity.EduCourseDescription;
import com.qiangzeng.learning.eduservice.entity.EduVideo;
import com.qiangzeng.learning.eduservice.entity.frontvo.CourseWeb;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.entity.vo.CoursePublish;
import com.qiangzeng.learning.eduservice.mapper.EduCourseMapper;
import com.qiangzeng.learning.eduservice.service.EduChapterService;
import com.qiangzeng.learning.eduservice.service.EduCourseDescriptionService;
import com.qiangzeng.learning.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiangzeng.learning.eduservice.service.EduVideoService;
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

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

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


    @Override
    public void updateCourse(CourseInfo courseInfo) {
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfo,eduCourse);
        int now=baseMapper.updateById(eduCourse);
        if(now==0){
            throw  new MyException(20001,"修改失败");
        }
        String cid=eduCourse.getId();
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        eduCourseDescription.setId(cid);
        eduCourseDescription.setDescription(courseInfo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);

    }


    @Override
    public CourseInfo getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(eduCourse,courseInfo);


        //2 查询描述表
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfo.setDescription(courseDescription.getDescription());

        return courseInfo;    }

    @Override
    public CoursePublish publishCourseInfo(String id) {
//调用mapper
        CoursePublish publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;    }

    @Override
    public void removeCourse(String courseId) {
        //1 根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);

        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if(result == 0) { //失败返回
            throw new MyException(20001,"删除失败");
        }
    }

    @Override
    public CourseWeb getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
