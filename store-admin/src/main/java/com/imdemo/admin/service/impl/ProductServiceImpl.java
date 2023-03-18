package com.imdemo.admin.service.impl;

import com.imdemo.admin.service.ProductService;
import com.imdemo.clients.ProductClient;
import com.imdemo.clients.SearchClient;
import com.imdemo.param.ProductSaveParam;
import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Product;
import com.imdemo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Time: 2022/12/4 16:02
 * @author: imdemo
 * description: 商品服务的实现类
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private ProductClient productClient;


    /**
     * 后台全部商品查询和搜索查询的方法
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

        R search = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束,结果:{}", search);
        return search;
    }

    /**
     * 进行商品数据保存
     *
     * @param productSaveParam
     * @return
     */
    @Override
    public R adminSave(ProductSaveParam productSaveParam) {

        R r = productClient.adminSave(productSaveParam);
        log.info("ProductServiceImpl.save业务结束,结果:{}", r);
        return r;
    }

    /**
     * 商品数据更新
     *
     * @param product
     * @return
     */
    @Override
    public R adminUpdate(Product product) {

        R r = productClient.adminUpdate(product);
        log.info("ProductServiceImpl.adminUpdate业务结束,结果:{}", r);
        return r;
    }

    /**
     * 商品删除
     *
     * @param productId
     * @return
     */
    @Override
    public R remove(Integer productId) {

        R r = productClient.adminRemove(productId);
        log.info("ProductServiceImpl.remove业务结束,结果:{}", r);
        return r;
    }
}
