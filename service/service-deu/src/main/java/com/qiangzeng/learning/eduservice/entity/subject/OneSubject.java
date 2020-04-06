package com.qiangzeng.learning.eduservice.entity.subject;

import lombok.Data;

import java.util.List;

/**
 * @author qiangzeng
 * @date 2020/4/6 下午5:18
 */

@Data
public class OneSubject {

    private String id;
    private String title;
    private List<TwoSubject> twoSubjectList;

}
