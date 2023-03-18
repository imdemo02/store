package com.imdemo.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imdemo.pojo.Address;
import com.imdemo.user.mapper.AddressMapper;
import com.imdemo.user.service.AddressService;
import com.imdemo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Time: 2022/11/28 18:51
 * @author: imdemo
 * description: 地址业务的实现类
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    /**
     * 根据用户id查询地址数据
     * 1.进行数据库的查询
     * 2.结果封装
     *
     * @param userId 用户id 已经校验完毕
     * @return 001 004
     */
    @Override
    public R list(Integer userId) {

        //1.封装查询参数
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        List<Address> addressList = addressMapper.selectList(queryWrapper);
        //2.结果封装
        R ok = R.ok("查询成功", addressList);
        log.info("AddressServiceImpl.list业务结束,结果:{}", ok);
        return ok;
    }

    /**
     * 保存地址数据，插入成功以后，要返回新的地址集合！
     *
     * @param address 地址数据已经校验完毕
     * @return 地址数据集合
     */
    @Override
    public R save(Address address) {
        //1.插入数据
        int rows = addressMapper.insert(address);
        if (rows == 0) {
            log.info("AddressServiceImpl.save业务结束,结果:{}", "地址保存失败!");
            return R.fail("地址保存失败!");
        }
        //2.插入成功  复用查询业务
        return list(address.getUserId());
    }

    /**
     * 按id删除地址
     *
     * @param id 地址id 校验完毕
     * @return 001  004
     */
    @Override
    public R remove(Integer id) {

        int rows = addressMapper.deleteById(id);
        if (rows == 0) {
            log.info("AddressServiceImpl.remove业务结束,结果:{}", "删除地址数据失败!");
            return R.fail("删除地址数据失败!");
        }
        log.info("AddressServiceImpl.remove业务结束,结果:{}", "地址删除成功!");
        return R.ok("地址删除成功!");
    }
}
