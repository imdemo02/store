package com.imdemo.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imdemo.category.mapper.CategoryMapper;
import com.imdemo.category.service.CategoryService;
import com.imdemo.clients.ProductClient;
import com.imdemo.param.PageParam;
import com.imdemo.param.ProductHotParam;
import com.imdemo.pojo.Category;
import com.imdemo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Time: 2022/11/29 14:51
 * @author: imdemo
 * description: 类别实现类
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * 根据类别名称查询 类别对象
     *
     * @param categoryName
     * @return
     */
    @Override
    public R byName(String categoryName) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", categoryName);

        Category category = categoryMapper.selectOne(queryWrapper);
        if (category == null) {
            log.info("CategoryServiceImpl.byName业务结束,结果:{}", "类别查询失败!");
            return R.fail("类别查询失败!");
        }
        log.info("CategoryServiceImpl.byName业务结束,结果:{}", "类别查询成功!");
        return R.ok("类比查询成功!", category);
    }

    /**
     * 根据传入的热门类别名称集合! 返回类别对应的id集合
     *
     * @param productHotParam
     * @return
     */
    @Override
    public R hotsCategory(ProductHotParam productHotParam) {

        //封装查询参数
        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("category_name", productHotParam.getCategoryName());
        //指定只查询 category_id  多对应的一列数据
        queryWrapper.select("category_id");

        //查询数据库
        List<Object> ids = categoryMapper.selectObjs(queryWrapper);

        R ok = R.ok("类别集合查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory业务结束,结果:{}", ok);
        return ok;
    }

    /**
     * 查询类别 进行返回
     *
     * @return
     */
    @Override
    public R list() {

        List<Category> categoryList = categoryMapper.selectList(null);
        R ok = R.ok("类别全部数据查询成功", categoryList);
        log.info("CategoryServiceImpl.list业务结束,结果:{}", ok);
        return ok;
    }

    /**
     * 用于后台调用的分页查询
     *
     * @param pageParam
     * @return
     */
    @Override
    public R listPage(PageParam pageParam) {

        Page<Category> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());

        page = categoryMapper.selectPage(page, null);

        return R.ok("类别分页数据查询成功!", page.getRecords(), page.getTotal());
    }

    /**
     * 添加类比信息
     *
     * @param category
     * @return
     */
    @Override
    public R adminSave(Category category) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("类别已经存在!添加失败!");
        }
        int insert = categoryMapper.insert(category);
        log.info("CategoryServiceImpl.save业务结束,结果:{}", insert);
        return R.ok("类别添加成功!");
    }

    /**
     * 删除数据
     *
     * @param categoryId
     * @return
     */
    @Override
    public R adminRemove(Integer categoryId) {

        Long count = productClient.adminCount(categoryId);

        if (count > 0) {
            return R.fail("类别删除失败,有:" + count + "件商品在引用!");
        }
        int i = categoryMapper.deleteById(categoryId);
        log.info("CategoryServiceImpl.adminRemove业务结束,结果:{}", i);

        return R.ok("类别删除成功!");
    }

    /**
     * 类别修改功能
     *
     * @param category
     * @return
     */
    @Override
    public R adminUpdate(Category category) {

        QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", category.getCategoryName());
        Long count = categoryMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.ok("类别已经存在,修改失败!");
        }
        int i = categoryMapper.updateById(category);
        log.info("CategoryServiceImpl.adminUpdate业务结束,结果:{}", i);
        return R.ok("类别修改成功!");
    }

    /**
     * 查询类别
     *
     * @param
     * @return
     */
    @Override
    public List<Category> categoryList() {
        List<Category> categoryList = categoryMapper.selectList(null);
        log.info("CategoryServiceImpl.categoryList业务结束,结果:{}", categoryList);
        return categoryList;
    }
}
