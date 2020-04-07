package com.qiangzeng.learning.eduservice.service;

import com.qiangzeng.learning.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qiangzeng.learning.eduservice.entity.course.ChapteData;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-07
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapteData>getByCourseId(String courseId);

     boolean deleteChapter(String chapterId);

    //2 根据课程id删除章节
    void removeChapterByCourseId(String courseId);

}
