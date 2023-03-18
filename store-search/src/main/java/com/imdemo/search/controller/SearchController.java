package com.imdemo.search.controller;

import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Product;
import com.imdemo.search.service.SearchService;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Time: 2022/11/30 21:31
 * @author: imdemo
 * description: 搜索模块的控制器
 */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("product")
    public R search(@RequestBody ProductSearchParam productSearchParam) {
        return searchService.search(productSearchParam);
    }

    @PostMapping("save")
    public R saveProduct(@RequestBody Product product) throws IOException {
        return searchService.save(product);
    }

    @PostMapping("remove")
    public R removeProduct(@RequestBody Integer productId) throws IOException {
        return searchService.remove(productId);
    }
}
