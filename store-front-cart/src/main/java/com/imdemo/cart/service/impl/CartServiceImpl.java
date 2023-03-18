package com.imdemo.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imdemo.cart.mapper.CartMapper;
import com.imdemo.cart.service.CartService;
import com.imdemo.clients.ProductClient;
import com.imdemo.param.CartSaveParam;
import com.imdemo.param.ProductCollectParam;
import com.imdemo.param.ProductIdParam;
import com.imdemo.pojo.Cart;
import com.imdemo.pojo.Product;
import com.imdemo.utils.R;
import com.imdemo.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Time: 2022/12/2 19:08
 * @author: imdemo
 * description: 购物车实现类
 */
@Service
@Slf4j
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ProductClient productClient;

    /**
     * 购物车添加方法
     *
     * @param cartSaveParam
     * @return 001 成功 002 已经存在  003 没有库存
     */
    @Override
    public R save(CartSaveParam cartSaveParam) {


        //查询商品数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cartSaveParam.getProductId());
        Product product = productClient.cdetail(productIdParam);

        if (product == null) {
            return R.fail("商品已经被删除,无法添加到购物车!");
        }
        //检查库存
        if (product.getProductNum() == 0) {

            R ok = R.ok("没有库存数据!无法购买");
            ok.setCode("003");
            log.info("CartServiceImpl.save业务结束,结果:{}", ok);
            return ok;
        }

        //检查是否添加过
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cartSaveParam.getUserId());
        queryWrapper.eq("product_id", cartSaveParam.getProductId());

        Cart cart = cartMapper.selectOne(queryWrapper);

        if (cart != null) {
            //证明购物车存在
            //原有的数量+1
            cart.setNum(cart.getNum() + 1);
            cartMapper.updateById(cart);

            //返回002  提示即可
            R ok = R.ok("购物车存在该商品,数量+1");
            ok.setCode("002");
            log.info("CartServiceImpl.save业务结束,结果:{}", ok);
            return ok;
        }
        //添加购物车
        cart = new Cart();
        cart.setNum(1);
        cart.setUserId(cartSaveParam.getUserId());
        cart.setProductId(cartSaveParam.getProductId());
        int rows = cartMapper.insert(cart);
        log.info("CartServiceImpl.save业务结束,结果:{}", rows);
        //结果封装和返回
        CartVo cartVo = new CartVo(product, cart);
        return R.ok("购物车数据添加成功!", cartVo);
    }

    /**
     * 购物车数据查看
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {

        //1.用户id查询  购物车数据
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Cart> cartList = cartMapper.selectList(queryWrapper);
        //2.判断是否存在，不存在，返回一个空集合
        if (cartList == null || cartList.size() == 0) {
            //必须要返回一个空集合
            cartList = new ArrayList<>();
            return R.ok("购物数据为空!", cartList);
        }
        //3.存在 获取读取商品的id集合，并且调用商品服务查询
        List<Integer> productIds = new ArrayList<>();
        for (Cart cart : cartList) {
            productIds.add(cart.getProductId());
        }
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);

        //商品集合 - 商品map  商品id = key  商品  = value
        //jdk 8 stream
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));
        //4.进行vo封装
        List<CartVo> cartVoList = new ArrayList<>();
        for (Cart cart : cartList) {
            CartVo cartVo = new CartVo(productMap.get(cart.getProductId()), cart);
            cartVoList.add(cartVo);
        }

        R r = R.ok("数据库数据查询成功!", cartVoList);
        log.info("CartServiceImpl.list业务结束,结果:{}", r);
        return r;
    }

    /**
     * 修改购物车数量
     *
     * @param cart
     * @return
     */
    @Override
    public R update(Cart cart) {

        //1.查询购物车数据
        ProductIdParam productIdParam = new ProductIdParam();
        productIdParam.setProductID(cart.getProductId());
        Product product = productClient.cdetail(productIdParam);

        //2.判断库存是否可用
        if (cart.getNum() > product.getProductNum()) {
            log.info("CartServiceImpl.update业务结束,结果:{}", "修改失败!库存不足!");
            return R.fail("修改失败!库存不足!");
        }
        //3.修改数据
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id", cart.getProductId());
        Cart newCart = cartMapper.selectOne(queryWrapper);
        newCart.setNum(cart.getNum());

        int rows = cartMapper.updateById(newCart);
        log.info("CartServiceImpl.update业务结束,结果:{}", rows);
        return R.ok("修改购物车数量成功!");
    }

    /**
     * 删除购物车数据
     *
     * @param cart
     * @return
     */
    @Override
    public R remove(Cart cart) {

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", cart.getUserId());
        queryWrapper.eq("product_id", cart.getProductId());

        int rows = cartMapper.delete(queryWrapper);
        log.info("CartServiceImpl.remove业务结束,结果:{}", rows);
        return R.ok("删除购物车数据成功!");

    }

    /**
     * 清空对应id的购物车项
     *
     * @param cartIds
     */
    @Override
    public void clearIds(List<Integer> cartIds) {
        cartMapper.deleteBatchIds(cartIds);
        log.info("CartServiceImpl.clearIds业务结束,结果:{}", cartIds);
    }

    /**
     * 检查购物车中是否有数据引用  影响删除业务
     *
     * @param productId
     * @return
     */
    @Override
    public R check(Integer productId) {

        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        Long count = cartMapper.selectCount(queryWrapper);
        if (count > 0) {
            return R.fail("购物车中有" + count + "项商品引用,无法删除!");
        }
        return R.ok("购物车中没有数据,可以删除!");
    }
}
