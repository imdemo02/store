package com.imdemo.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imdemo.param.OrderParam;
import com.imdemo.param.PageParam;
import com.imdemo.pojo.Order;
import com.imdemo.utils.R;

/**
 * @Time: 2022/12/3 10:37
 * @author: imdemo
 * description:
 */
public interface OrderService extends IService<Order> {

    /**
     * 订单保存方法
     *
     * @param orderParam
     * @return
     */
    R save(OrderParam orderParam);

    /**
     * 分组查询购物车订单数据
     *
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 后台管理查询订单数据
     *
     * @param pageParam
     * @return
     */
    R adminList(PageParam pageParam);

    /**
     * 检查订单中是否有商品引用  影响删除
     *
     * @param productId
     * @return
     */
    R check(Integer productId);
}
