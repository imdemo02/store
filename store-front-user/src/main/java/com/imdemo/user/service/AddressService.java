package com.imdemo.user.service;

import com.imdemo.pojo.Address;
import com.imdemo.utils.R;

/**
 * @Time: 2022/11/28 18:48
 * @author: imdemo
 * description:
 */
public interface AddressService {

    /**
     * 根据用户id查询地址数据
     *
     * @param userId 用户id 已经校验完毕
     * @return 001 004
     */
    R list(Integer userId);

    /**
     * 保存地址数据，插入成功以后，要返回新的地址集合！
     *
     * @param address 地址数据已经校验完毕
     * @return 地址数据集合
     */
    R save(Address address);

    /**
     * 按id删除地址
     *
     * @param id  地址id 校验完毕
     * @return  001  004
     */
    R remove(Integer id);
}
