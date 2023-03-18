package com.imdemo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Time: 2022/11/28 16:23
 * @author: imdemo
 * description: 用户登录参数实体
 */
@Data
public class UserLoginParam {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
