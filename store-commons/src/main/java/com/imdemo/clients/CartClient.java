package com.imdemo.clients;

import com.imdemo.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Time: 2022/12/6 15:42
 * @author: imdemo
 * description: 购物车调用fegin客户端
 */
@FeignClient("cart-service")
public interface CartClient {

    @PostMapping("cart/remove/check")
    R check(@RequestBody Integer productId);
}
