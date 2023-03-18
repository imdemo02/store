package com.imdemo.collect.service;

import com.imdemo.pojo.Collect;
import com.imdemo.utils.R;

/**
 * @Time: 2022/12/2 13:44
 * @author: imdemo
 * description:
 */
public interface CollectService {

    /**
     * 收藏添加的方法
     *
     * @param collect
     * @return
     */
    R save(Collect collect);

    /**
     * 收藏查看的方法  根据用户id 查询商品信息集合
     *
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 根据用户id 和商品id删除 收藏数据
     *
     * @param collect
     * @return
     */
    R remove(Collect collect);

    /**
     * 根据商品id删除
     *
     * @param productId
     * @return
     */
    R removeByPid(Integer productId);
}
