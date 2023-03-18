package com.imdemo.product.controller;

import com.imdemo.pojo.Product;
import com.imdemo.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Time: 2022/11/30 17:35
 * @author: imdemo
 * description: 搜索服务调用商品服务的controller
 */
@RestController
@RequestMapping("product")
public class ProductSearchController {

    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public List<Product> allList() {

        return productService.allList();
    }
}
