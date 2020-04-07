package com.qiangzeng.learning.eduservice.controller;


import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduChapter;
import com.qiangzeng.learning.eduservice.entity.course.ChapteData;
import com.qiangzeng.learning.eduservice.service.EduChapterService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-07
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
public class EduChapterController {


    @Autowired
    private EduChapterService eduChapterService;

    @ApiModelProperty(value = "/创建章节")
    @RequestMapping(value = "createChapter",method = RequestMethod.POST)
    public ResponseResult createChapter(EduChapter eduChapter){

        eduChapterService.save(eduChapter);
        return ResponseResult.success();
    }


    @ApiModelProperty(value = "/获取章节")
    @RequestMapping(value = "createChapter/{courseId}",method = RequestMethod.POST)
    public ResponseResult getChapter(@PathVariable String courseId){

       List<ChapteData>chapterList= eduChapterService.getByCourseId(courseId);
       return ResponseResult.success(chapterList);

    }


    //根据章节id查询
    @RequestMapping(value ="getChapterInfo/{chapterId}",method = RequestMethod.GET)
    public ResponseResult getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return ResponseResult.success(eduChapter);
    }


    @ApiModelProperty(value = "修改章节")
    @RequestMapping(value = "updateChapter",method = RequestMethod.POST)
    public ResponseResult updateChapter(@RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return ResponseResult.success();
    }

    //删除的方法
    @DeleteMapping("{chapterId}")
    public ResponseResult deleteChapter(@PathVariable String chapterId) {
        boolean flag = eduChapterService.deleteChapter(chapterId);
        if(flag) {
            return ResponseResult.success();
        } else {
            return ResponseResult.error("删除失败");
        }

    }

}

