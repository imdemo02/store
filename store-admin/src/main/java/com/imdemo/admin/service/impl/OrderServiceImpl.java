package com.imdemo.admin.service.impl;

import com.imdemo.admin.service.OrderService;
import com.imdemo.clients.OrderClient;
import com.imdemo.param.PageParam;
import com.imdemo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Time: 2022/12/4 19:24
 * @author: imdemo
 * description: 订单业务实现类
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderClient orderClient;

    /**
     * 查询订单数据
     *
     * @param pageParam
     * @return
     */
    @Override
    public R list(PageParam pageParam) {

        R r = orderClient.list(pageParam);
        log.info("OrderServiceImpl.list业务结束,结果:{}", r);
        return r;
    }
}
