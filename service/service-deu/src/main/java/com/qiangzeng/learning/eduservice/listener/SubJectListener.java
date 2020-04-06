package com.qiangzeng.learning.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiangzeng.learning.base.exceptionhandler.MyException;
import com.qiangzeng.learning.eduservice.entity.EduSubject;
import com.qiangzeng.learning.eduservice.entity.excel.SubjectExcelData;
import com.qiangzeng.learning.eduservice.service.EduSubjectService;

/**
 * @author qiangzeng
 * @date 2020/4/6 下午4:05
 */
public class SubJectListener extends AnalysisEventListener<SubjectExcelData> {

    /**
     * 不能用spring管理，需要自己创建
     */
    private EduSubjectService subjectService;


    public SubJectListener() {
    }

    public SubJectListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectExcelData subjectExcelData, AnalysisContext analysisContext) {
            if(subjectExcelData == null) {
                throw new MyException(20001,"文件数据为空");
            }

            //一行一行读取，每次读取有两个值，第一个值一级分类，第二个值二级分类
            //判断一级分类是否重复
            EduSubject existOneSubject = this.existOneSubject(subjectService, subjectExcelData.getOneName());
            if(existOneSubject == null) { //没有相同一级分类，进行添加
                existOneSubject = new EduSubject();
                existOneSubject.setParentId("0");
                existOneSubject.setTitle(subjectExcelData.getOneName());//一级分类名称
                subjectService.save(existOneSubject);
            }

            //获取一级分类id值
            String oneId = existOneSubject.getId();

            //添加二级分类
            //判断二级分类是否重复
            EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectExcelData.getTwoName(), oneId);
            if(existTwoSubject == null) {
                existTwoSubject = new EduSubject();
                existTwoSubject.setParentId(oneId);
                existTwoSubject.setTitle(subjectExcelData.getTwoName());//二级分类名称
                subjectService.save(existTwoSubject);
            }
        }

        //判断一级分类不能重复添加
        private EduSubject existOneSubject(EduSubjectService subjectService,String name) {
            QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
            wrapper.eq("title",name);
            wrapper.eq("parent_id","0");
            EduSubject oneSubject = subjectService.getOne(wrapper);
            return oneSubject;
        }

        //判断二级分类不能重复添加
        private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String oneId) {
            QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
            wrapper.eq("title",name);
            wrapper.eq("parent_id",oneId);
            EduSubject twoSubject = subjectService.getOne(wrapper);
            return twoSubject;

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
