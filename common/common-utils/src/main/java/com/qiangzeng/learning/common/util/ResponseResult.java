package com.qiangzeng.learning.common.util;

import com.qiangzeng.learning.common.enums.ResultCode;
import lombok.Data;

/**
 * @author qiangzeng
 * @date 2020/4/3 下午9:20
 */

@Data
public class ResponseResult {

    //是否成功
    private Boolean success;
    //返回码
    private Integer code;
    //返回信息
    private String message;
    //返回数据
    private Object data;

    public ResponseResult(Object data) {
        this.success = true;
        this.code=ResultCode.SUCCESS.getCode();
        this.data = data;
    }

    public ResponseResult(Boolean success,Integer code,String message) {
        this.success = success;
        this.code=code;
        this.message=message;
    }

    public static ResponseResult success(){
        return new ResponseResult(true,ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage());
    }

    public static ResponseResult success(Object data){
        return new ResponseResult(data);
    }

    public static ResponseResult error(String message){
        return new ResponseResult(false,ResultCode.FAILE.getCode(),message);
    }

}
