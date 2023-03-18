package com.imdemo.user.controller;

import com.imdemo.param.UserCheckParam;
import com.imdemo.param.UserLoginParam;
import com.imdemo.pojo.User;
import com.imdemo.user.service.UserService;
import com.imdemo.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time: 2022/11/28 13:50
 * @author: imdemo
 * description: 用户模块的控制器
 * @RequestBody 注解用于接受前端的json数据类型并且转成对应的实体类
 * @Validated 用于让属性上对应的校验注解生效
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 检查账号是否可用的接口
     *
     * @param userCheckParam 接受检查的账号实体   内部有参数校验注解
     * @param result         获取校验结果的实体对象
     * @return 返回封装结果R对象
     */
    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result) {
        //检查是否符合校验注解的规则   符合  false  不符合  true
        if (result.hasErrors()) {
            return R.fail("账号为Null，不可使用");
        }
        return userService.check(userCheckParam);
    }

    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result) {

        if (result.hasErrors()) {
            //若果请求异常，证明请求参数不符合校验注解要求
            return R.fail("参数异常，不可注册");
        }
        return userService.register(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数异常，不可登录!");
        }
        return userService.login(userLoginParam);
    }
}
