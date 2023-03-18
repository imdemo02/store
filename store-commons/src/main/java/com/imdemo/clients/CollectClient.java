package com.imdemo.clients;

import com.imdemo.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Time: 2022/12/6 15:56
 * @author: imdemo
 * description: 收藏对应的客户端
 */
@FeignClient("collect-service")
public interface CollectClient {

    @PostMapping("collect/remove/product")
    R remove(@RequestBody Integer productId);
}
