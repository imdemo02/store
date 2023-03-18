package com.imdemo.admin.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;

/**
 * @Time: 2022/12/3 19:15
 * @author: imdemo
 * description: 接收登陆信息的param
 */
@Data
public class AdminUserParam {

    @Length(min = 6)
    private String userAccount; //账号
    @Length(min = 6)
    private String userPassword; //密码
    @NotBlank
    private String verCode;  //验证码


}
