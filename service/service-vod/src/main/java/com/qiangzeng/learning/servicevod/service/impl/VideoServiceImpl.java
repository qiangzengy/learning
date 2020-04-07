package com.qiangzeng.learning.servicevod.service.impl;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.qiangzeng.learning.base.exceptionhandler.MyException;
import com.qiangzeng.learning.servicevod.service.VideoService;
import com.qiangzeng.learning.servicevod.util.InitVodCilent;
import com.qiangzeng.learning.servicevod.util.UploadVideoUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qiangzeng
 * @date 2020/4/7 下午9:01
 */

@Service
public class VideoServiceImpl implements VideoService {

    @Override
    public void removeMoreAlyVideo(List<String> videoIdList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodCilent.initVodClient(UploadVideoUtils.ACCESS_KEY_ID, UploadVideoUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();

            //videoIdList值转换成 1,2,3
            String videoIds = StringUtils.join(videoIdList.toArray(), ",");

            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        }catch(Exception e) {
            e.printStackTrace();
            throw new MyException(20001,"删除视频失败");
        }
    }
}
