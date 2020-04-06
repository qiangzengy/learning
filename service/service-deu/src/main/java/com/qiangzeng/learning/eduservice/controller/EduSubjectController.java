package com.qiangzeng.learning.eduservice.controller;


import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.subject.OneSubject;
import com.qiangzeng.learning.eduservice.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/eduservice/edu-subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    @ApiOperation(value = "读取Excel数据并存入数据库")
    @RequestMapping(value = "radeExcel",method = RequestMethod.POST)
    public ResponseResult radeExcel(MultipartFile file) {

        try{
            subjectService.saveSubject(file,subjectService);
            return ResponseResult.success();

        }catch (IOException e) {

            e.printStackTrace();
            return ResponseResult.error("读取excel数据shi bshib");

        }
    }


    @ApiOperation(value = "获所有取课程列表")
    @RequestMapping(value = "getSubjectList",method = RequestMethod.GET)
    public ResponseResult getSubjectList( ) {

       List<OneSubject>oneSubjectList= subjectService.getSubjectList();
        return ResponseResult.success(oneSubjectList);

    }

}

