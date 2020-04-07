package com.qiangzeng.learning.eduservice.entity.course;

import lombok.Data;

import java.util.List;

/**
 * @author qiangzeng
 * @date 2020/4/7 下午1:20
 */

@Data
public class ChapteData {

    private String id;
    private String title;
    List<VideoData>videoDataList;


}
