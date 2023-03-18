package com.imdemo.doc;

import com.imdemo.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Time: 2022/11/30 17:59
 * @author: imdemo
 * description: 存储商品搜索数据的实体类
 */
@Data
@NoArgsConstructor
public class ProductDoc extends Product {
    /**
     * 商品名称，商品标题和商品描述的综合值
     */
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(), product.getProductName(), product.getCategoryId(), product.getProductTitle(),
                product.getProductIntro(), product.getProductPicture(), product.getProductPrice(),
                product.getProductSellingPrice(), product.getProductNum(), product.getProductSales());

        this.all = product.getProductName() + product.getProductTitle() + product.getProductIntro();

    }
}
