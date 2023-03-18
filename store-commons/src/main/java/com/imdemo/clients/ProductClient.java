package com.imdemo.clients;

import com.imdemo.param.ProductCollectParam;
import com.imdemo.param.ProductIdParam;
import com.imdemo.param.ProductSaveParam;
import com.imdemo.pojo.Product;
import com.imdemo.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Time: 2022/11/30 17:45
 * @author: imdemo
 * description: 商品服务调用客户端
 */
@FeignClient("product-service")
public interface ProductClient {

    /**
     * 搜索服务调用商品服务 进行全部数据查询  用于搜索数据库同步数据
     *
     * @return
     */
    @GetMapping("product/list")
    List<Product> allList();

    /**
     * 收藏服务调用商品服务  根据传入的id集合 查询数据
     *
     * @param productCollectParam
     * @return
     */
    @PostMapping("product/collect/list")
    R productIds(@RequestBody ProductCollectParam productCollectParam);

    /**
     * 购物车服务调用商品服务  根据传入的id查询商品的详情
     *
     * @param productIdParam
     * @return
     */
    @PostMapping("product/cart/detail")
    Product cdetail(@RequestBody ProductIdParam productIdParam);

    /**
     * 购物车调用商品服务  根据id集合查询商品集合
     *
     * @param productCollectParam
     * @return
     */
    @PostMapping("product/cart/list")
    List<Product> cartList(@RequestBody ProductCollectParam productCollectParam);

    @PostMapping("product/admin/count")
    Long adminCount(@RequestBody Integer categoryId);

    @PostMapping("product/admin/save")
    R adminSave(@RequestBody ProductSaveParam productSaveParam);

    @PostMapping("product/admin/update")
    R adminUpdate(@RequestBody Product product);

    @PostMapping("product/admin/remove")
    R adminRemove(@RequestBody Integer productId);
}
