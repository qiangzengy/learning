package com.qiangzeng.learning.eduservice.service;

import com.qiangzeng.learning.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-07
 */
public interface EduVideoService extends IService<EduVideo> {

    //1 根据课程id删除小节
    void removeVideoByCourseId(String courseId);

}
