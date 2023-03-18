package com.imdemo.admin.service;

import com.imdemo.param.ProductSaveParam;
import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Product;
import com.imdemo.utils.R;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Time: 2022/12/4 15:59
 * @author: imdemo
 * description:
 */
public interface ProductService {

    /**
     * 后台全部商品查询和搜索查询的方法
     *
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 进行商品数据保存
     *
     * @param productSaveParam
     * @return
     */
    R adminSave(ProductSaveParam productSaveParam);

    /**
     * 商品数据更新
     *
     * @param product
     * @return
     */
    R adminUpdate(Product product);

    /**
     * 商品删除
     *
     * @param productId
     * @return
     */
    R remove(Integer productId);
}
