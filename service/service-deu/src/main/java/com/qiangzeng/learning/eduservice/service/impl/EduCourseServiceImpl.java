package com.qiangzeng.learning.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.base.exceptionhandler.MyException;
import com.qiangzeng.learning.eduservice.entity.EduCourse;
import com.qiangzeng.learning.eduservice.entity.EduCourseDescription;
import com.qiangzeng.learning.eduservice.entity.EduVideo;
import com.qiangzeng.learning.eduservice.entity.frontvo.CourseFront;
import com.qiangzeng.learning.eduservice.entity.frontvo.CourseWeb;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.entity.vo.CoursePublish;
import com.qiangzeng.learning.eduservice.mapper.EduCourseMapper;
import com.qiangzeng.learning.eduservice.service.EduChapterService;
import com.qiangzeng.learning.eduservice.service.EduCourseDescriptionService;
import com.qiangzeng.learning.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiangzeng.learning.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @Override
    public List<EduCourse> getByTeacherId(String tedcherId) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("teacher_id",tedcherId);
        List<EduCourse>eduCourseList=baseMapper.selectList(queryWrapper);
        return eduCourseList;
    }


    @Override
    public Map<String, Object> getCourseFrontList(Page page, CourseFront courseFront) {

        String teacherId=courseFront.getTeacherId();

        String subjectParentId=courseFront.getSubjectParentId();

        String subjectId=courseFront.getSubjectId();

        //销量排序
        String buyCountSort=courseFront.getBuyCountSort();

        //最新时间排序
        String gmtCreateSort=courseFront.getGmtCreateSort();

        //价格排序
        String priceSort=courseFront.getPriceSort();
        QueryWrapper queryWrapper=new QueryWrapper();

        if(!StringUtils.isEmpty(teacherId)){
            queryWrapper.eq("teacher_id",teacherId);
        }
        if(!StringUtils.isEmpty(subjectParentId)) { //一级分类
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(subjectId)) { //二级分类
            queryWrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(buyCountSort)) { //关注度
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(gmtCreateSort)) { //最新
            queryWrapper.orderByDesc("gmt_create");
        }

        if (!StringUtils.isEmpty(priceSort)) {//价格
            queryWrapper.orderByDesc("price");
        }

            baseMapper.selectPage(page,queryWrapper);

        List<EduCourse> records = page.getRecords();
        long current = page.getCurrent();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        boolean hasNext = page.hasNext();//下一页
        boolean hasPrevious = page.hasPrevious();//上一页

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;

    }
}
