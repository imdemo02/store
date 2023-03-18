package com.imdemo.admin.controller;

import com.imdemo.admin.service.CategoryService;
import com.imdemo.param.PageParam;
import com.imdemo.pojo.Category;
import com.imdemo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Time: 2022/12/4 14:20
 * @author: imdemo
 * description: 类别controller
 */
@RestController
@RequestMapping("category")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public R pageList(PageParam pageParam) {

        return categoryService.pageList(pageParam);
    }

    @PostMapping("save")
    public R save(Category category) {

        return categoryService.save(category);
    }

    @PostMapping("remove")
    public R remove(Integer categoryId) {

        return categoryService.remove(categoryId);
    }

    @PostMapping("update")
    public R update(Category category) {

        return categoryService.update(category);
    }

    @GetMapping("categoryList")
    public List<Category> categoryList() {

        return categoryService.categoryList();
    }

}
