package com.imdemo.clients;

import com.imdemo.param.CartListParam;
import com.imdemo.param.PageParam;
import com.imdemo.pojo.User;
import com.imdemo.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Time: 2022/12/4 11:11
 * @author: imdemo
 * description: 用户客户端
 */
@FeignClient("user-service")
public interface UserClient {

    @PostMapping("user/admin/list")
    R adminListPage(@RequestBody PageParam pageParam);

    @PostMapping("user/admin/remove")
    R adminRemove(@RequestBody CartListParam cartListParam);

    @PostMapping("user/admin/update")
    R adminUpdate(@RequestBody User user);

    @PostMapping("user/admin/save")
    R adminSave(@RequestBody User user);
}
