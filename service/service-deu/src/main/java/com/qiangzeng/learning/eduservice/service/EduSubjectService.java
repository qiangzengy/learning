package com.qiangzeng.learning.eduservice.service;

import com.qiangzeng.learning.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface EduSubjectService extends IService<EduSubject> {

    public void saveSubject(MultipartFile file, EduSubjectService subjectService) throws IOException;

}
