package com.qiangzeng.learning.eduservice.client;

import com.qiangzeng.learning.common.util.ResponseResult;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qiangzeng
 * @date 2020/4/8 下午12:09
 */
@Component
public class VideoFeignClient implements VideoClient{

    @Override
    public ResponseResult removeAlyVideo(String id) {
        return ResponseResult.error("删除失败");
    }

    @Override
    public ResponseResult deleteBatch(List<String> videoIdList) {
        return ResponseResult.error("多个删除失败");
    }
}
