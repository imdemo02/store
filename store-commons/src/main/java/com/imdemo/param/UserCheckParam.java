package com.imdemo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Time: 2022/11/28 13:39
 * @author: imdemo
 * description: 接受前端参数的param
 * TODO:使用jsr303的注解  进行参数校验
 */
@Data
public class UserCheckParam {
    //前端进行赋值时不能为null并且不能为空字符串 " "
    @NotBlank
    private String userName;
}
