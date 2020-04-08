package com.qiangzeng.learning.ucservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author qiangzeng
 * @date 2020/4/8 下午4:02
 */

@Data
public class LoginVo {

    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;

}
