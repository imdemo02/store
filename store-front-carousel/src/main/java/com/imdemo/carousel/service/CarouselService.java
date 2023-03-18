package com.imdemo.carousel.service;

import com.imdemo.utils.R;

/**
 * @Time: 2022/11/29 12:57
 * @author: imdemo
 * description:
 */
public interface CarouselService {
    /**
     * 查询优先级最高的六条轮播图数据
     *
     * @return
     */
    public R list();
}
