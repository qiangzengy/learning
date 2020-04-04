package com.qiangzeng.learning.common.enums;

import lombok.Getter;

/**
 * @author qiangzeng
 * @date 2020/4/3 下午9:03
 */

@Getter
public enum ResultCode  {

    SUCCESS(20000, "成功"),
    FAILE(20001, "失败"),
    ;

    private Integer code;
    private String message;

    ResultCode(Integer code, String message ) {
        this.code = code;
        this.message = message;
    }

}
