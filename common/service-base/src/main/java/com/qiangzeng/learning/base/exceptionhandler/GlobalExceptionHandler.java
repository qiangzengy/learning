package com.qiangzeng.learning.base.exceptionhandler;

import com.qiangzeng.learning.common.util.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author qiangzeng
 * @date 2020/4/4 下午3:46
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody //为了返回数据
    public ResponseResult error(Exception e) {
        e.printStackTrace();
        return ResponseResult.error("全局异常处理");
    }


    //自定义异常执行这个方法
    @ExceptionHandler(MyException.class)
    @ResponseBody //为了返回数据
    public ResponseResult error(MyException e) {
        e.printStackTrace();
        return ResponseResult.error(e.getMsg());
    }
}

