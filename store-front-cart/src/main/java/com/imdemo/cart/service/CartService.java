package com.imdemo.cart.service;

import com.imdemo.param.CartSaveParam;
import com.imdemo.pojo.Cart;
import com.imdemo.utils.R;

import java.util.List;

/**
 * @Time: 2022/12/2 19:07
 * @author: imdemo
 * description:
 */
public interface CartService {


    /**
     * 购物车添加方法
     *
     * @param cartSaveParam
     * @return 001 成功  002 已经存在  003 没有库存
     */
    R save(CartSaveParam cartSaveParam);

    /**
     * 购物车数据查看
     *
     * @param userId
     * @return 确保要返回一个数组或者集合
     */
    R list(Integer userId);

    /**
     * 修改购物车数量
     *
     * @param cart
     * @return
     */
    R update(Cart cart);

    /**
     * 删除购物车数据
     *
     * @param cart
     * @return
     */
    R remove(Cart cart);

    /**
     * 清空对应id的购物车项
     *
     * @param cartIds
     */
    void clearIds(List<Integer> cartIds);

    /**
     * 检查购物车中是否有数据引用  影响删除业务
     *
     * @param productId
     * @return
     */
    R check(Integer productId);
}
