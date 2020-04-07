package com.qiangzeng.learning.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiangzeng.learning.base.exceptionhandler.MyException;
import com.qiangzeng.learning.eduservice.entity.EduChapter;
import com.qiangzeng.learning.eduservice.entity.EduVideo;
import com.qiangzeng.learning.eduservice.entity.course.ChapteData;
import com.qiangzeng.learning.eduservice.entity.course.VideoData;
import com.qiangzeng.learning.eduservice.mapper.EduChapterMapper;
import com.qiangzeng.learning.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qiangzeng.learning.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-07
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapteData> getByCourseId(String courseId) {
        //1 根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> queryWrapper=new QueryWrapper();
        queryWrapper.eq("course_id",courseId);
        List<EduChapter>eduChapterList=baseMapper.selectList(queryWrapper);

        //创建list集合，用于最终封装数据
        List<ChapteData> finalList = new ArrayList<>();

        for (EduChapter data:eduChapterList) {

            //2 根据章节id查询课程里面所有的小节
            QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
            wrapperVideo.eq("chapter_id",data.getId());
            List<EduVideo> eduVideoList = eduVideoService.list(wrapperVideo);

            //创建ChapteData对象
            ChapteData chapteData=new ChapteData();
            //将EduChapter拷贝到ChapteData
            BeanUtils.copyProperties(data,chapteData);
            //创建VideoData的list集合
            List<VideoData> videoDataList=new ArrayList<>();

            for (int i=0;i<eduVideoList.size();i++){
                VideoData videoData=new VideoData();
                //将EduVideo对象拷贝到VideoData
                BeanUtils.copyProperties(eduVideoList.get(i),videoData);
                videoDataList.add(videoData);
            }
            chapteData.setVideoDataList(videoDataList);
            finalList.add(chapteData);
        }
        return finalList;
    }


    @Override
    public boolean deleteChapter(String chapterId) {
        int now=baseMapper.deleteById(chapterId);
        if (now==0){
            throw new MyException(20001,"删除失败");
        }
        QueryWrapper <EduVideo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        return eduVideoService.remove(queryWrapper);
    }
}
