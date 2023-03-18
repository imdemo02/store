package com.imdemo.user.controller;

import com.imdemo.param.CartListParam;
import com.imdemo.param.PageParam;
import com.imdemo.pojo.User;
import com.imdemo.user.service.UserService;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time: 2022/12/4 11:03
 * @author: imdemo
 * description: 用户被后台管理调用的cotroller
 */
@RestController
@RequestMapping("user")
public class UserAdminController {

    @Autowired
    private UserService userService;

    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam) {

        return userService.pageList(pageParam);
    }

    @PostMapping("admin/remove")
    public R remove(@RequestBody CartListParam cartListParam) {
        return userService.remove(cartListParam.getUserId());
    }

    @PostMapping("admin/update")
    public R update(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("admin/save")
    public R save(@RequestBody User user) {

        return userService.save(user);
    }

}
