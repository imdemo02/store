package com.imdemo.category.service;

import com.imdemo.param.PageParam;
import com.imdemo.param.ProductHotParam;
import com.imdemo.pojo.Category;
import com.imdemo.utils.R;

import java.util.List;

/**
 * @Time: 2022/11/29 14:49
 * @author: imdemo
 * description:
 */
public interface CategoryService {

    /**
     * 根据类别名称查询 类别对象
     *
     * @param categoryName
     * @return
     */
    R byName(String categoryName);

    /**
     * 根据传入的热门类别名称集合! 返回类别对应的id集合
     *
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);

    /**
     * 查询类别 进行返回
     *
     * @return
     */
    R list();

    /**
     * 用于后台调用的分页查询
     *
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 添加类比信息
     *
     * @param category
     * @return
     */
    R adminSave(Category category);

    /**
     * 删除数据
     *
     * @param categoryId
     * @return
     */
    R adminRemove(Integer categoryId);

    /**
     * 类别修改功能
     *
     * @param category
     * @return
     */
    R adminUpdate(Category category);

    /**
     * 查询类比
     *
     * @return
     */
    List<Category> categoryList();
}
