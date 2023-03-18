package com.imdemo.admin.controller;

import com.imdemo.admin.service.OrderService;
import com.imdemo.param.PageParam;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time: 2022/12/4 19:21
 * @author: imdemo
 * description: 订单的控制controller
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("list")
    public R list(PageParam pageParam) {

        return orderService.list(pageParam);
    }
}
