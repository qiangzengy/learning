package com.qiangzeng.learning.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author qiangzeng
 * @date 2020/4/6 下午3:46
 */

@Data
public class SubjectExcelData {

    @ExcelProperty(index =0)
    private String oneName;

    @ExcelProperty(index=1)
    private String twoName;


}
