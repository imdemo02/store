package com.imdemo.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imdemo.clients.ProductClient;
import com.imdemo.collect.mapper.CollectMapper;
import com.imdemo.collect.service.CollectService;
import com.imdemo.param.ProductCollectParam;
import com.imdemo.pojo.Collect;
import com.imdemo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time: 2022/12/2 13:46
 * @author: imdemo
 * description: 收藏模块的实现类
 */
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * 收藏添加的方法
     *
     * @param collect
     * @return
     */
    @Override
    public R save(Collect collect) {

        //1.先查询是否存在
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId());
        queryWrapper.eq("product_id", collect.getProductId());

        Long count = collectMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("收藏已经添加,无需二次添加!");
        }

        //2.不存在   添加收藏
        //补充下时间
        collect.setCollectTime(System.currentTimeMillis());
        int rows = collectMapper.insert(collect);
        log.info("CollectServiceImpl.save业务结束,结果:{}", rows);
        return R.ok("收藏成功!");
    }

    /**
     * 收藏查看的方法
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.select("product_id");
        List<Object> idsObject = collectMapper.selectObjs(queryWrapper);
        ProductCollectParam productCollectParam = new ProductCollectParam();

        List<Integer> ids = new ArrayList<>();
        for (Object o : idsObject) {
            ids.add((Integer) o);
        }
        productCollectParam.setProductIds(ids);
        R r = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list业务结束,结果:{}", r);
        return r;
    }

    /**
     * 根据用户id 和商品id删除 收藏数据
     *
     * @param collect
     * @return
     */
    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", collect.getUserId());
        queryWrapper.eq("Product_id", collect.getProductId());

        int rows = collectMapper.delete(queryWrapper);
        log.info("CollectServiceImpl.remove业务结束,结果:{}", rows);
        return R.ok("收藏删除成功!");
    }

    /**
     * 根据商品id删除
     *
     * @param productId
     * @return
     */
    @Override
    public R removeByPid(Integer productId) {

        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        int rows = collectMapper.delete(queryWrapper);

        log.info("CollectServiceImpl.removeByPid业务结束,结果:{}", rows);

        return R.ok("收藏删除成功!");
    }
}
