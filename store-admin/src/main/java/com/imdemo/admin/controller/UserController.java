package com.imdemo.admin.controller;

import com.imdemo.param.CartListParam;
import com.imdemo.param.PageParam;

import com.imdemo.admin.service.UserService;
import com.imdemo.pojo.User;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time: 2022/12/4 11:15
 * @author: imdemo
 * description: 用户模块调用的controller
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public R userList(PageParam pageParam) {

        return userService.userList(pageParam);
    }

    @PostMapping("remove")
    public R userRemove(CartListParam cartListParam) {

        return userService.userRemove(cartListParam);
    }

    @PostMapping("update")
    public R userUpdate(User user) {

        return userService.userUpdate(user);
    }

    @PostMapping("save")
    public R userSave(User user) {

        return userService.userSave(user);
    }
}
