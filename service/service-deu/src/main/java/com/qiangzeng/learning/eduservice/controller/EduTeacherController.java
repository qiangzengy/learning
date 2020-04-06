package com.qiangzeng.learning.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qiangzeng.learning.common.util.ResponseResult;
import com.qiangzeng.learning.eduservice.entity.EduTeacher;
import com.qiangzeng.learning.eduservice.entity.vo.TeacherQuery;
import com.qiangzeng.learning.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qiangzeng
 * @since 2020-04-03
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "查询所有")
    @RequestMapping(value = "findAll",method = RequestMethod.GET)
    public ResponseResult findAll(){
        List<EduTeacher> list=teacherService.list(null);
        return ResponseResult.success(list);
    }

    @ApiOperation(value = "分页查询")
    @RequestMapping(value = "pageData/{curret}/{size}",method = RequestMethod.GET)
    public ResponseResult pageData(@PathVariable long curret,@PathVariable long size){
        Page<EduTeacher>page=new Page<>(curret,size);
        teacherService.page(page,null);
        return ResponseResult.success(teacherService.page(page,null));
    }

    @ApiOperation(value = "根据Id删除")
    @RequestMapping(value = "removeById/{id}",method = RequestMethod.DELETE)
    public ResponseResult removeById(@PathVariable String id){
        //TODO 感觉有问题，随便传id返回也是true
       EduTeacher teacher = teacherService.getById(id);
       if (teacher==null){
           return ResponseResult.error("查询不到数据，删除失败");
       }
       teacherService.removeById(id);
       return ResponseResult.success();
    }


    @ApiOperation(value = "增加或修改")
    @RequestMapping(value = "saveOrUpdate",method =RequestMethod.POST)
    public ResponseResult saveOrUpdate(@RequestBody EduTeacher eduTeacher){
        try{
            teacherService.saveOrUpdate(eduTeacher);
            return ResponseResult.success();
        }catch (Exception e){
            return ResponseResult.error("新增失败");
        }

    }

    @ApiOperation(value = "分页条件查询")
    @RequestMapping(value = "pageCondition/{curret}/{size}",method = RequestMethod.POST)
    public ResponseResult pageCondition(@PathVariable long curret, @PathVariable long size, @RequestBody(required = false) TeacherQuery teacherQuery){

        //创建分页
        Page<EduTeacher>page=new Page<>(curret,size);
        //创建条件
        QueryWrapper<EduTeacher>queryWrapper=new QueryWrapper<>();
        String name=teacherQuery.getName();
        Integer level=teacherQuery.getLevel();
        String startTime=teacherQuery.getStartTime();
        String endTime=teacherQuery.getEndTime();
        if (!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(level!=null) {
            queryWrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(startTime)){
            queryWrapper.gt("gmt_create",startTime);
        }
        if (!StringUtils.isEmpty(endTime)){
            queryWrapper.gt("gmt_modified",endTime);
        }
        IPage<EduTeacher> pages=teacherService.page(page,queryWrapper);
        return ResponseResult.success(pages);
    }

    @ApiOperation(value = "根据讲师id进行查询")
    @RequestMapping(value = "getById/{id}",method = RequestMethod.GET)
    public ResponseResult getById(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return ResponseResult.success(teacher);
    }

}

