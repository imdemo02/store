package com.imdemo.admin.controller;

import com.imdemo.admin.param.AdminUserParam;
import com.imdemo.admin.pojo.AdminUser;
import com.imdemo.admin.service.AdminUserService;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Time: 2022/12/3 19:21
 * @author: imdemo
 * description: 后台管理  用户处理controller
 */

@RestController
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("user/login")
    public R login(@Validated AdminUserParam adminUserParam, BindingResult result, HttpSession session) {

        if (result.hasErrors()) {
            return R.fail("核心参数为null,登录失败!");
        }

        //验证码校验
        String captcha = (String) session.getAttribute("captcha");
        if (!adminUserParam.getVerCode().equals(captcha)) {
            return R.fail("验证码错误!");
        }
        AdminUser user = adminUserService.login(adminUserParam);
        if (user == null) {
            return R.fail("登录失败! 账号或密码错误");
        }
        session.setAttribute("userInfo", user);
        return R.ok("登录成功!");
    }

    @GetMapping("user/logout")
    public R logout(HttpSession session) {

        //清空session
        session.invalidate();
        return R.ok("退出登录成功!");
    }


}
