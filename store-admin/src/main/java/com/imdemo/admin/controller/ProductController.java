package com.imdemo.admin.controller;

import com.imdemo.admin.service.ProductService;
import com.imdemo.admin.utils.AliyunOSSUtils;
import com.imdemo.param.ProductSaveParam;
import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Product;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


/**
 * @Time: 2022/12/4 15:54
 * @author: imdemo
 * description: 商品后台管理的controller
 */
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AliyunOSSUtils aliyunOSSUtils;

    @GetMapping("list")
    public R list(ProductSearchParam productSearchParam) {

        return productService.search(productSearchParam);
    }

    @PostMapping("upload")
    public R upload(MultipartFile img) throws Exception {

        //获取文件名
        String fileName = img.getOriginalFilename();
        //名字可能会重复  加一个uuid  去掉uuid的中划线
        fileName = UUID.randomUUID().toString().replaceAll("-", "") + fileName;
        //获取文件类型
        String contentType = img.getContentType();
        //获取内容
        byte[] content = img.getBytes();
        //存储时间  小时
        int hours = 1000;

        String url = aliyunOSSUtils.uploadImage(fileName, content, contentType, hours);
        System.out.println("url = " + url);
        return R.ok("图片上传成功!", url);

    }

    @PostMapping("save")
    public R save(ProductSaveParam productSaveParam) {

        return productService.adminSave(productSaveParam);

    }

    @PostMapping("update")
    public R update(Product product) {

        return productService.adminUpdate(product);

    }

    @PostMapping("remove")
    public R remove(Integer productId) {

        return productService.remove(productId);

    }
}
