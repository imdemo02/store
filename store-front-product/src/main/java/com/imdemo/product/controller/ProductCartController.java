package com.imdemo.product.controller;

import com.imdemo.param.ProductCollectParam;
import com.imdemo.param.ProductIdParam;
import com.imdemo.pojo.Product;
import com.imdemo.product.service.ProductService;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Time: 2022/12/2 16:45
 * @author: imdemo
 * description: 购物车调用商品服务的controller
 */
@RestController
@RequestMapping("product")
public class ProductCartController {


    @Autowired
    private ProductService productService;

    @PostMapping("cart/detail")
    public Product cdetail(@RequestBody @Validated ProductIdParam productIdParam, BindingResult result) {

        if (result.hasErrors()) {
            return null;
        }
        R detail = productService.detail(productIdParam.getProductID());
        Product product = (Product) detail.getData();
        return product;
    }

    @PostMapping("cart/list")
    public List<Product> cartList(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult result) {

        if (result.hasErrors()) {
            return new ArrayList<Product>();
        }

        return productService.carList(productCollectParam.getProductIds());
    }

}
