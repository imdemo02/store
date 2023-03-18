package com.imdemo.carousel.controller;

import com.imdemo.carousel.service.CarouselService;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time: 2022/11/29 12:52
 * @author: imdemo
 * description: 轮播图控制器
 */
@RestController
@RequestMapping("carousel")
public class CarouselController {


    @Autowired
    private CarouselService carouselService;

    /**
     * 查询轮播图数据  只要优先级最高的得6条
     *
     * @return
     */
    @PostMapping("list")
    public R list() {
        return carouselService.list();
    }
}
