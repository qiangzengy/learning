package com.qiangzeng.learning.eduservice.controller;


import com.qiangzeng.learning.base.exceptionhandler.MyException;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.client.VideoClient;
import com.qiangzeng.learning.eduservice.entity.EduVideo;
import com.qiangzeng.learning.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-07
 */
@RestController
@RequestMapping("/eduservice/edu-video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VideoClient videoClient;

    @ApiModelProperty("创建小节")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseResult createVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return ResponseResult.success();

    }


    //删除小节，删除对应阿里云视频
    @DeleteMapping("{id}")
    public ResponseResult deleteVideo(@PathVariable String id) {
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            ResponseResult result = videoClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == 20001) {
                throw new MyException(20001,"删除视频失败");
            }
        }
        //删除小节
        eduVideoService.removeById(id);
        return ResponseResult.success();
    }



}

