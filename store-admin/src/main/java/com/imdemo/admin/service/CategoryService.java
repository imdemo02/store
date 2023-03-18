package com.imdemo.admin.service;

import com.imdemo.param.PageParam;
import com.imdemo.pojo.Category;
import com.imdemo.utils.R;

import java.util.List;

/**
 * @Time: 2022/12/4 14:23
 * @author: imdemo
 * description:
 */
public interface CategoryService {

    /**
     * 类比分页查询方法
     *
     * @param pageParam
     * @return
     */
    R pageList(PageParam pageParam);

    /**
     * 进行分类数据添加
     *
     * @param category
     * @return
     */
    R save(Category category);

    /**
     * 根据id删除类别数据
     *
     * @param categoryId
     * @return
     */
    R remove(Integer categoryId);

    /**
     * 类别信息修改
     *
     * @param category
     * @return
     */
    R update(Category category);

    /**
     * 查询类别
     *
     * @param
     * @return
     */
    List<Category> categoryList();
}
