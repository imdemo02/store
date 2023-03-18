package com.imdemo.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.imdemo.param.ProductHotParam;
import com.imdemo.param.ProductIdsParam;
import com.imdemo.param.ProductSaveParam;
import com.imdemo.param.ProductSearchParam;
import com.imdemo.pojo.Product;
import com.imdemo.to.OrderToProduct;
import com.imdemo.utils.R;

import java.util.List;

/**
 * @Time: 2022/11/29 15:29
 * @author: imdemo
 * description:
 */
public interface ProductService extends IService<Product> {

    /**
     * 但类别名称,查询热门商品  至多7条数据
     *
     * @param categoryName 类别名称
     * @return 返回R的集合
     */
    R promo(String categoryName);

    /**
     * 多类别热门商品查询 ，根据类别名称集合 之多查询7条
     *
     * @param productHotParam 类别名称集合
     * @return r
     */
    R hots(ProductHotParam productHotParam);

    /**
     * 查询类别商品集合
     *
     * @return
     */
    R clist();

    /**
     * 通过用性的业务
     * 如果传入了id 根据id查询  并分页
     * 没有 则查询全部
     *
     * @param productIdsParam
     * @return
     */
    R bycategory(ProductIdsParam productIdsParam);

    /**
     * 根据商品id查询商品的详细信息
     *
     * @param productID
     * @return
     */
    R detail(Integer productID);

    /**
     * 查询商品对应的图片详情集合
     *
     * @param productID
     * @return
     */
    R pictures(Integer productID);

    /**
     * 搜索服务调用  获取全部商品数据
     * 进行同步
     *
     * @return
     */
    List<Product> allList();

    /**
     * 搜索业务 需要调用搜索服务
     *
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 根据商品id集合查询商品信息
     *
     * @param productIds
     * @return
     */
    R ids(List<Integer> productIds);

    /**
     * 根据id集合查询商品集合
     *
     * @param productIds
     * @return
     */
    List<Product> carList(List<Integer> productIds);

    /**
     * 修改库存和增加销售量
     *
     * @param orderToProducts
     */
    void subNumber(List<OrderToProduct> orderToProducts);

    /**
     * 类别对应的商品数量查询
     *
     * @param categoryId
     * @return
     */
    Long adminCount(Integer categoryId);

    /**
     * 商品保存业务
     *
     * @param productSaveParam
     * @return
     */
    R adminSave(ProductSaveParam productSaveParam);

    /**
     * 商品数据更新
     *
     * @param product
     * @return
     */
    R adminUpdate(Product product);

    /**
     * 根据商品id删除
     *
     * @param productId
     * @return
     */
    R adminRemove(Integer productId);
}
