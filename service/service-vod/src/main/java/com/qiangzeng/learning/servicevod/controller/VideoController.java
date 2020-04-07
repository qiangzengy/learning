package com.qiangzeng.learning.servicevod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.qiangzeng.learning.base.exceptionhandler.MyException;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.servicevod.service.VideoService;
import com.qiangzeng.learning.servicevod.util.InitVodCilent;
import com.qiangzeng.learning.servicevod.util.UploadVideoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author qiangzeng
 * @date 2020/4/7 下午7:31
 */

@RestController
@RequestMapping("/eduvod/vod-video")
public class VideoController {


    @Autowired
    private VideoService videoService;


    @RequestMapping(value = "getVideoId",method = RequestMethod.POST)
    public ResponseResult getVideoId(MultipartFile file){

        UploadVideoUtils uploadVideoUtils=new UploadVideoUtils();
        String videoId=uploadVideoUtils.uploadVideo(file);
        return ResponseResult.success(videoId);

    }

    //根据视频id删除阿里云视频
    @RequestMapping(value = "removeAlyVideo/{id}",method = RequestMethod.DELETE)
    public ResponseResult removeAlyVideo(@PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(UploadVideoUtils.ACCESS_KEY_ID, UploadVideoUtils.ACCESS_KEY_SECRET);

            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return ResponseResult.success();
        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }
    }

    //删除多个阿里云视频的方法
    //参数多个视频id  List videoIdList
    @RequestMapping(value = "delete-batch",method = RequestMethod.DELETE)
    public ResponseResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList) {
        videoService.removeMoreAlyVideo(videoIdList);
        return ResponseResult.success();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public ResponseResult getPlayAuth(@PathVariable String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client =
                    InitVodCilent.initVodClient(UploadVideoUtils.ACCESS_KEY_ID, UploadVideoUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return ResponseResult.success();
        }catch(Exception e) {
            throw new MyException(20001,"获取凭证失败");
        }
    }


}
