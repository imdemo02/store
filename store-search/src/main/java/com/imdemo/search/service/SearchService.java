package com.imdemo.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Product;
import com.imdemo.utils.R;

import java.io.IOException;

/**
 * @Time: 2022/11/30 21:35
 * @author: imdemo
 * description:
 */
public interface SearchService {

    /**
     * 根据关键字和分页进行数据库数据查询
     *
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 同步调用 进行商品的插入 覆盖更新
     * 商品同步:插入和更新
     *
     * @param product
     * @return
     */
    R save(Product product) throws IOException;

    /**
     * 进行es库的商品删除
     *
     * @param productId
     * @return
     */
    R remove(Integer productId) throws IOException;
}
