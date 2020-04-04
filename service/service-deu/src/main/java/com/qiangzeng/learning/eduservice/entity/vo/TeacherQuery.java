package com.qiangzeng.learning.eduservice.entity.vo;

import lombok.Data;


/**
 * @author qiangzeng
 * @date 2020/4/4 下午2:15
 */

@Data
public class TeacherQuery {

    private String name;
    private Integer level;
    private String startTime;
    private String endTime;
}
