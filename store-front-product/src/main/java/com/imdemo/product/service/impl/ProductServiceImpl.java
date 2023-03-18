package com.imdemo.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imdemo.clients.*;
import com.imdemo.param.ProductHotParam;
import com.imdemo.param.ProductIdsParam;
import com.imdemo.param.ProductSaveParam;
import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Picture;
import com.imdemo.pojo.Product;
import com.imdemo.product.mapper.PictureMapper;
import com.imdemo.product.mapper.ProductMapper;
import com.imdemo.to.OrderToProduct;
import com.imdemo.utils.R;
import com.imdemo.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Time: 2022/11/29 15:31
 * @author: imdemo
 * description: 商品服务的实现类
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    /**
     * 引入feign客户端需要,在启动类添加配置注解
     */
    @Autowired
    private CategoryClient categoryClient;


    @Autowired
    private PictureMapper pictureMapper;

    @Autowired
    private SearchClient searchClient;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private CollectClient collectClient;

    /**
     * 单类别名称,查询热门商品  至多7条数据
     * 1.根据类别名称 调用 feign客户端访问类别服务 获取类别数据
     * 2.成功 继续根据类别id查询商品数据  【热门 销售量倒序  查询 7条】
     * 3.结果封装
     *
     * @param categoryName 类别名称
     * @return 返回R的集合
     */
    @Cacheable(value = "list.product", key = "#categoryName", cacheManager = "cacheManagerHour")
    @Override
    public R promo(String categoryName) {

        R r = categoryClient.byName(categoryName);

        if (R.FAIL_CODE.equals(r.getCode())) {
            log.info("ProductServiceImpl.promo业务结束,结果:{}", "类别查询失败!");
            return r;
        }
        //获取类别数据   类别服务中 data = category   -----feign {json} ----- product 服务 LinkedHashMap jackson
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) r.getData();
        //获取id
        Integer categoryId = (Integer) map.get("category_id");
        //查询
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("category_id", categoryId);
        //倒序
        queryWrapper.orderByDesc("product_sales");

        //分页
        Page<Product> page = new Page<>(1, 7);

        //返回的是包装数据! 内部有对应的商品集合  也有分页的参数
        page = productMapper.selectPage(page, queryWrapper);

        //指定页的数据
        List<Product> productList = page.getRecords();


        log.info("ProductServiceImpl.promo业务结束,结果:{}", productList);
        return R.ok("数据查询成功", productList);
    }

    /**
     * 多类别热门商品查询 ，根据类别名称集合 之多查询7条
     * 1、调用类别服务
     * 2、类别集合id查询商品
     * 3、结果集封装即可
     *
     * @param productHotParam 类别名称集合
     * @return r
     */
    @Cacheable(value = "list.product", key = "#productHotParam.categoryName")
    @Override
    public R hots(ProductHotParam productHotParam) {

        R r = categoryClient.hots(productHotParam);

        if (R.FAIL_CODE.equals(r.getCode())) {
            log.info("ProductServiceImpl.hots业务结束,结果:{}", r.getMsg());
            return r;
        }
        //获取id集合
        List<Object> ids = (List<Object>) r.getData();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("category_id", ids);
        queryWrapper.orderByDesc("product_sales");

        Page<Product> page = new Page<>(1, 7);
        page = productMapper.selectPage(page, queryWrapper);

        List<Product> productList = page.getRecords();
        R ok = R.ok("多类别热门商品查询成功!", productList);
        log.info("ProductServiceImpl.hots业务结束,结果:{}", ok);
        return ok;
    }

    /**
     * 查询类别商品集合
     *
     * @return
     */
    @Cacheable(value = "list.category", key = "#root.methodName", cacheManager = "cacheManagerDay")
    @Override
    public R clist() {
        R r = categoryClient.list();
        log.info("ProductServiceImpl.clist业务结束,结果:{}", r);
        return r;
    }

    /**
     * 通过用性的业务
     * 如果传入了id 根据id查询  并分页
     * 没有 则查询全部
     *
     * @param productIdsParam
     * @return
     */
    @Cacheable(value = "list.product", key = "#productIdsParam.categoryID+'_'+#productIdsParam.currentPage+'_'+#productIdsParam.pageSize")
    @Override
    public R bycategory(ProductIdsParam productIdsParam) {

        List<Integer> categoryId = productIdsParam.getCategoryID();
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if (!categoryId.isEmpty()) {
            queryWrapper.in("category_id", categoryId);
        }
        Page<Product> page = new Page<>(productIdsParam.getCurrentPage(), productIdsParam.getPageSize());
        page = productMapper.selectPage(page, queryWrapper);

        //结果集封装
        R ok = R.ok("查询成功!", page.getRecords(), page.getTotal());
        log.info("ProductServiceImpl.bycategory业务结束,结果:{}", ok);
        return ok;
    }

    /**
     * 根据商品id查询商品的详细信息
     *
     * @param productID
     * @return
     */
    @Cacheable(value = "product", key = "#productID")
    @Override
    public R detail(Integer productID) {

        Product product = productMapper.selectById(productID);
        R ok = R.ok(product);
        log.info("ProductServiceImpl.detail业务结束,结果:{}", ok);
        return ok;
    }

    /**
     * 查询商品对应的图片详情集合
     *
     * @param productID
     * @return
     */
    @Cacheable(value = "picture", key = "#productID")
    @Override
    public R pictures(Integer productID) {

        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productID);

        List<Picture> pictureList = pictureMapper.selectList(queryWrapper);

        R ok = R.ok(pictureList);
        log.info("ProductServiceImpl.pictures业务结束,结果:{}", ok);
        return ok;
    }


    /**
     * 搜索服务调用  获取全部商品数据
     * 进行同步
     *
     * @return
     */
    @Override
    public List<Product> allList() {

        List<Product> productList = productMapper.selectList(null);
        log.info("ProductServiceImpl.allList业务结束,结果:{}", productList.size());
        return productList;
    }

    /**
     * 搜索业务 需要调用搜索服务
     *
     * @param productSearchParam
     * @return
     */
    @Override
    public R search(ProductSearchParam productSearchParam) {

        R r = searchClient.search(productSearchParam);
        log.info("ProductServiceImpl.search业务结束,结果:{}", r);
        return r;
    }

    /**
     * 根据商品id集合查询商品信息
     *
     * @param productIds
     * @return
     */
    @Cacheable(value = "list.product", key = "#productIds")
    @Override
    public R ids(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);
        R r = R.ok("类别信息查询成功!", productList);
        log.info("ProductServiceImpl.ids业务结束,结果:{}", r);
        return r;
    }

    /**
     * 根据id集合查询商品集合
     *
     * @param productIds
     * @return
     */
    @Override
    public List<Product> carList(List<Integer> productIds) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("product_id", productIds);

        List<Product> productList = productMapper.selectList(queryWrapper);
        log.info("ProductServiceImpl.carList业务结束,结果:{}", productList);
        return productList;
    }

    /**
     * 修改库存和增加销售量
     *
     * @param orderToProducts
     */
    @Override
    public void subNumber(List<OrderToProduct> orderToProducts) {

        //将集合转成map key--productId  value--orderToProducts
        Map<Integer, OrderToProduct> map = orderToProducts.stream().collect(Collectors.toMap(OrderToProduct::getProductId, v -> v));
        //获取商品的id集合
        Set<Integer> productIds = map.keySet();
        //查询对应的商品信息
        List<Product> productList = productMapper.selectBatchIds(productIds);
        //修改商品信息
        for (Product product : productList) {
            Integer num = map.get(product.getProductId()).getNum();
            //减库存
            product.setProductNum(product.getProductNum() - num);
            //增加销量
            product.setProductSales(product.getProductSales() + num);
        }
        //批量更新
        this.updateBatchById(productList);
        log.info("ProductServiceImpl.subNumber业务结束,结果:{}", "库存和销售量的数据修改成功");
    }

    /**
     * 类别对应的商品数量查询
     *
     * @param categoryId
     * @return
     */
    @Override
    public Long adminCount(Integer categoryId) {

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_id", categoryId);

        Long count = productMapper.selectCount(queryWrapper);
        log.info("ProductServiceImpl.adminCount业务结束,结果:{}", count);
        return count;
    }

    /**
     * 商品保存业务
     * 1.商品数据保存
     * 2.商品的图片详情切割和保存
     * 3.搜索数据库的数据添加
     * 4.清空相关的缓存数据
     *
     * @param productSaveParam
     * @return
     */
    @CacheEvict(value = "list.product", allEntries = true)
    @Override
    public R adminSave(ProductSaveParam productSaveParam) {

        Product product = new Product();
        //参数赋值
        BeanUtils.copyProperties(productSaveParam, product);

        //商品数据插入
        int rows = productMapper.insert(product);

        log.info("ProductServiceImpl.adminSave业务结束,结果:{}", rows);

        //商品图片获取
        String pictures = productSaveParam.getPictures();
        //$ + - * | / ？^符号在正则表达示中有相应的不同意义。
        //一般来讲只需要加[]、或是\\即可
        if (!StringUtils.isEmpty(pictures)) {
            String[] urls = pictures.split("\\+");

            for (String url : urls) {
                Picture picture = new Picture();
                picture.setProductId(product.getProductId());
                picture.setProductPicture(url);
                //插入商品的图片
                pictureMapper.insert(picture);
            }
        }

        //同步搜索服务
        searchClient.saveOrUpdate(product);
        return R.ok("商品数据添加成功!");
    }

    /**
     * 商品数据更新
     * 1.更新商品数据
     * 2.同步搜索服务数据即可
     *
     * @param product
     * @return
     */
    @Override
    public R adminUpdate(Product product) {

        productMapper.updateById(product);

        //同步搜索服务
        searchClient.saveOrUpdate(product);
        return R.ok("商品数据更新成功!");
    }

    /**
     * 根据商品id删除
     * <p>
     * 1、检查购物车
     * 2、检查订单
     * 3、删除商品
     * 4、删除商品相关的图片
     * 5、删除收藏
     * 6、进行es数据同步
     * 6、清空缓存
     *
     * @param productId
     * @return
     */

    @Caching(
            evict = {
                    @CacheEvict(value = "list.product", allEntries = true),
                    @CacheEvict(value = "product", key = "#productId")
            }
    )
    @Override
    public R adminRemove(Integer productId) {

        //1、检查购物车
        R r = cartClient.check(productId);
        if ("004".equals(r.getCode())) {
            log.info("ProductServiceImpl.adminRemove业务结束,结果:{}", r.getMsg());
            return r;
        }
        //2、检查订单
        r = orderClient.check(productId);
        if ("004".equals(r.getCode())) {
            log.info("ProductServiceImpl.adminRemove业务结束,结果:{}", r.getMsg());
            return r;
        }

        //删除商品
        productMapper.deleteById(productId);
        //删除商品图片
        QueryWrapper<Picture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        pictureMapper.delete(queryWrapper);

        //删除本收藏中和商品有关的
        collectClient.remove(productId);

        //同步数据
        searchClient.remove(productId);
        return R.ok("商品删除成功!");
    }
}
