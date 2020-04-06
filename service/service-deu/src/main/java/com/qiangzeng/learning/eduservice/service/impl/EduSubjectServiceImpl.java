package com.qiangzeng.learning.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.qiangzeng.learning.eduservice.entity.EduSubject;
import com.qiangzeng.learning.eduservice.entity.excel.SubjectExcelData;
import com.qiangzeng.learning.eduservice.listener.SubJectListener;
import com.qiangzeng.learning.eduservice.mapper.EduSubjectMapper;
import com.qiangzeng.learning.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    public void saveSubject(MultipartFile file,EduSubjectService subjectService) throws IOException {

        //获取文件流
        InputStream stream=file.getInputStream();

        EasyExcel.read(stream, SubjectExcelData.class,new SubJectListener(subjectService)).sheet().doRead();

    }

}
