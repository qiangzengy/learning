package com.qiangzeng.learning.eduservice.client;

import com.qiangzeng.learning.common.util.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author qiangzeng
 * @date 2020/4/7 下午11:06
 */

@FeignClient(value = "service-vod",fallback = VideoFeignClient.class)
@Component
public interface VideoClient {

    @RequestMapping(value = "eduvod/vod-video/removeAlyVideo/{id}",method = RequestMethod.DELETE)
    ResponseResult removeAlyVideo(@PathVariable("id") String id);

    @RequestMapping(value = "eduvod/vod-video/delete-batch",method = RequestMethod.DELETE)
    ResponseResult deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);


}
