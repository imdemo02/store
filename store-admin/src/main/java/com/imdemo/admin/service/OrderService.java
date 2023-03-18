package com.imdemo.admin.service;

import com.imdemo.param.PageParam;
import com.imdemo.utils.R;

/**
 * @Time: 2022/12/4 19:23
 * @author: imdemo
 * description:
 */
public interface OrderService {

    /**
     * 查询订单数据
     *
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);
}
