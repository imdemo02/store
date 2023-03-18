package com.imdemo.param;

import com.imdemo.pojo.Product;
import lombok.Data;

/**
 * @Time: 2022/12/5 17:08
 * @author: imdemo
 * description: 商品数据保存param
 */
@Data
public class ProductSaveParam extends Product {

    /**
     * 保存商品详情的图片地址  图片之间使用 + 拼接
     */
    private String pictures;
}
