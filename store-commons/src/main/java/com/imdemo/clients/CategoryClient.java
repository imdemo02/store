package com.imdemo.clients;

import com.imdemo.param.PageParam;
import com.imdemo.param.ProductHotParam;
import com.imdemo.pojo.Category;
import com.imdemo.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Time: 2022/11/29 15:01
 * @author: imdemo
 * description: 类别的调用接口
 */
@FeignClient("category-service")
public interface CategoryClient {

    @GetMapping("category/promo/{categoryName}")
    R byName(@PathVariable String categoryName);

    @PostMapping("category/hots")
    R hots(@RequestBody ProductHotParam productHotParam);

    @GetMapping("category/list")
    R list();

    @PostMapping("category/admin/list")
    R adminPageList(@RequestBody PageParam pageParam);

    @PostMapping("category/admin/save")
    R adminSave(@RequestBody Category category);

    @PostMapping("category/admin/remove")
    R adminRemove(@RequestBody Integer categoryId);

    @PostMapping("category/admin/update")
    R adminUpdate(@RequestBody Category category);

    @PostMapping("category/admin/categoryList")
    List<Category> categoryList();

}
