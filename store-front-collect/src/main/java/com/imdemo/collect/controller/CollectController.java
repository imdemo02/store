package com.imdemo.collect.controller;

import com.imdemo.collect.service.CollectService;
import com.imdemo.pojo.Collect;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Time: 2022/12/2 13:42
 * @author: imdemo
 * description: 收藏模块的控制器
 */
@RestController
@RequestMapping("collect")
public class CollectController {


    @Autowired
    private CollectService collectService;

    @PostMapping("save")
    public R save(@RequestBody Collect collect) {
        return collectService.save(collect);
    }

    @PostMapping("list")
    public R list(@RequestBody Collect collect) {
        return collectService.list(collect.getUserId());
    }

    @PostMapping("remove")
    public R remove(@RequestBody Collect collect) {
        return collectService.remove(collect);
    }

    @PostMapping("remove/product")
    public R removeByPid(@RequestBody Integer productId) {
        return collectService.removeByPid(productId);
    }
}
