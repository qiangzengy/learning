package com.qiangzeng.learning.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiangzeng.learning.eduservice.entity.EduSubject;
import com.qiangzeng.learning.eduservice.entity.excel.SubjectExcelData;
import com.qiangzeng.learning.eduservice.entity.subject.OneSubject;
import com.qiangzeng.learning.eduservice.entity.subject.TwoSubject;
import com.qiangzeng.learning.eduservice.listener.SubJectListener;
import com.qiangzeng.learning.eduservice.mapper.EduSubjectMapper;
import com.qiangzeng.learning.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    public void saveSubject(MultipartFile file,EduSubjectService subjectService) throws IOException {

        //获取文件流
        InputStream stream=file.getInputStream();

        EasyExcel.read(stream, SubjectExcelData.class,new SubJectListener(subjectService)).sheet().doRead();

    }


    @Override
    public List<OneSubject> getSubjectList() {
        List<OneSubject> oneSubjectList=new ArrayList<>();
        //获取一级类目
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("parent_id","0");
        List<EduSubject> eduSubjectList= baseMapper.selectList(queryWrapper);
        //获取该一级类目的二级类目
        for (EduSubject  data :  eduSubjectList) {
            QueryWrapper queryWrapperTwo=new QueryWrapper();
            queryWrapperTwo.eq("parent_id",data.getId());
            List<TwoSubject> twoSubjectList=baseMapper.selectList(queryWrapperTwo);
            OneSubject oneSubject=new OneSubject();
            //将一级类目的属性写入OneSubject
            BeanUtils.copyProperties(data,oneSubject);
            //将二级类目的属性写入OneSubject
            oneSubject.setTwoSubjectList(twoSubjectList);
            oneSubjectList.add(oneSubject);
        }
        return oneSubjectList;
    }
}
