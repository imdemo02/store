package com.imdemo.clients;

import com.imdemo.param.PageParam;
import com.imdemo.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Time: 2022/12/4 19:14
 * @author: imdemo
 * description: 订单对应的fegin的客户端
 */
@FeignClient("order-service")
public interface OrderClient {

    @PostMapping("order/remove/check")
    R check(@RequestBody Integer productId);

    @PostMapping("order/admin/list")
    R list(@RequestBody PageParam pageParam);
}
