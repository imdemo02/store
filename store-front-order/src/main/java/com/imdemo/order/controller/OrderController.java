package com.imdemo.order.controller;

import com.imdemo.order.service.OrderService;
import com.imdemo.param.CartListParam;
import com.imdemo.param.OrderParam;
import com.imdemo.param.PageParam;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time: 2022/12/3 10:35
 * @author: imdemo
 * description: 订单模块的controller
 */
@RestController
@RequestMapping("order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("save")
    public R save(@RequestBody OrderParam orderParam) {

        return orderService.save(orderParam);
    }

    @PostMapping("list")
    public R list(@RequestBody @Validated CartListParam cartListParam, BindingResult result) {

        if (result.hasErrors()) {
            return R.fail("参数异常,插叙吧失败!");
        }
        return orderService.list(cartListParam.getUserId());
    }

    @PostMapping("admin/list")
    public R adminList(@RequestBody PageParam pageParam) {

        return orderService.adminList(pageParam);

    }

    @PostMapping("remove/check")
    public R check(@RequestBody Integer productId) {

        return orderService.check(productId);

    }

}
