package com.imdemo.clients;

import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Product;
import com.imdemo.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Time: 2022/11/30 22:33
 * @author: imdemo
 * description: 搜索服务客户端
 */
@FeignClient("search-service")
public interface SearchClient {

    @PostMapping("search/product")
    R search(@RequestBody ProductSearchParam productSearchParam);

    @PostMapping("search/save")
    R saveOrUpdate(@RequestBody Product product);

    @PostMapping("search/remove")
    R remove(@RequestBody Integer productId);

}
