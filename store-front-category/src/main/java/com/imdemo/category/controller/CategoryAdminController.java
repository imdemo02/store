package com.imdemo.category.controller;

import com.imdemo.category.service.CategoryService;
import com.imdemo.param.PageParam;
import com.imdemo.pojo.Category;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Time: 2022/12/4 14:09
 * @author: imdemo
 * description: 后台管理的分类处理controller
 */
@RestController
@RequestMapping("category")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam) {

        return categoryService.listPage(pageParam);
    }

    @PostMapping("admin/save")
    public R adminSave(@RequestBody Category category) {

        return categoryService.adminSave(category);
    }

    @PostMapping("admin/remove")
    public R adminRemove(@RequestBody Integer categoryId) {

        return categoryService.adminRemove(categoryId);
    }

    @PostMapping("admin/update")
    public R adminUpdate(@RequestBody Category category) {

        return categoryService.adminUpdate(category);
    }

    @PostMapping("admin/categoryList")
    public List<Category> categoryList() {
        return categoryService.categoryList();
    }
}
