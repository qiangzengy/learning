package com.qiangzeng.learning.eduservice.controller;


import com.aliyuncs.IAcsClient;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduComment;
import com.qiangzeng.learning.eduservice.entity.vo.CourseInfo;
import com.qiangzeng.learning.eduservice.service.EduCommentService;
import com.qiangzeng.learning.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/eduservice/edu-comment")
public class EduCommentController {

    @Autowired
    private EduCommentService eduCommentService;

    @ApiModelProperty(value = "添加评论")
    @RequestMapping(value = "createCourse",method = RequestMethod.POST)
    public ResponseResult createComment(@RequestBody EduComment eduComment){
        boolean restlt=eduCommentService.save(eduComment);
        if(restlt){
            return ResponseResult.success();
        }else {
            return ResponseResult.error("评论失败");
        }

    }

    @ApiModelProperty(value = "分页查询评论")
    @RequestMapping(value = "pageComment/{curret}/{size}",method = RequestMethod.GET)
    public ResponseResult pageComment(@PathVariable long curret,@PathVariable long size){
        Page<EduComment>page=new Page<>(curret,size);
        IPage<EduComment>pages=eduCommentService.page(page,null);
        return ResponseResult.success(pages);

    }

}

