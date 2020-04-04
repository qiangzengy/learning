package com.qiangzeng.learning.base.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qiangzeng
 * @date 2020/4/4 下午5:27
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException{

    private Integer code;
    private String msg;

}
