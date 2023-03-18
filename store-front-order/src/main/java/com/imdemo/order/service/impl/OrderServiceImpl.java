package com.imdemo.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imdemo.clients.ProductClient;
import com.imdemo.order.mapper.OrderMapper;
import com.imdemo.order.service.OrderService;
import com.imdemo.param.OrderParam;
import com.imdemo.param.PageParam;
import com.imdemo.param.ProductCollectParam;
import com.imdemo.param.ProductPromoParam;
import com.imdemo.pojo.Order;
import com.imdemo.pojo.Product;
import com.imdemo.to.OrderToProduct;
import com.imdemo.utils.R;
import com.imdemo.vo.AdminOrderVo;
import com.imdemo.vo.CartVo;
import com.imdemo.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Time: 2022/12/3 10:39
 * @author: imdemo
 * description: 订单业务实现类
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 订单保存方法
     * 1.将购物车数据转成订单数据
     * 2.进行订单数据的批量插入
     * 3.发送商品库存修改消息
     * 4.发送购物车库存修改消息
     *
     * @param orderParam
     * @return
     */
    //批量操作  开启事务
    @Transactional
    @Override
    public R save(OrderParam orderParam) {

        //准备数据
        //要删除的购物车id集合
        List<Integer> cartIds = new ArrayList<>();
        //要修改商品库存的参数
        List<OrderToProduct> orderToProducts = new ArrayList<>();
        //转成的Order数据
        List<Order> orderList = new ArrayList<>();

        //生成数据
        Integer userId = orderParam.getUserId();
        long orderId = System.currentTimeMillis();

        for (CartVo cartVo : orderParam.getProducts()) {
            //保存要删除的购物车项id
            cartIds.add(cartVo.getId());
            //保存商品服务要修改的数据
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setNum(cartVo.getNum());
            orderToProduct.setProductId(cartVo.getProductID());
            orderToProducts.add(orderToProduct);

            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderTime(orderId);
            order.setProductId(cartVo.getProductID());
            order.setUserId(userId);
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());

            orderList.add(order);
        }

        //订单数据批量保存
        this.saveBatch(orderList);

        //发送购物车消息
        rabbitTemplate.convertAndSend("topic.ex", "clear.cart", cartIds);
        //发送商品服务消息
        rabbitTemplate.convertAndSend("topic.ex", "sub.number", orderToProducts);

        R ok = R.ok("订单生成成功!");
        log.info("OrderServiceImpl.save业务结束,结果:{}", ok);
        return ok;
    }

    /**
     * 分组查询购物车订单数据
     * 1.查询用户对应的全部订单项
     * 2.利用stream进行订单分组  orderId
     * 3.查询订单全部商品集合 并用stream 组成map
     * 4.封装返回的orderVo对象
     *
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {

        //根据用户id查询订单集合  全部的订单
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Order> list = this.list(queryWrapper);

        //分组  按照订单id分组
        Map<Long, List<Order>> orderMap = list.stream().collect(Collectors.groupingBy(Order::getOrderId));

        //查询商品数据  拿到订单中的商品id集合
        List<Integer> productIds = list.stream().map(Order::getProductId).collect(Collectors.toList());

        //复用客户端  查询id集合对应的商品集合
        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);
        //转成map集合  作用是 当获取每个订单的时候 要获取去对应的商品信息时  可以根据商品的key 去获取对应的商品信息
        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //结果封装
        List<List<OrderVo>> result = new ArrayList<>();

        //遍历订单项集合
        for (List<Order> orders : orderMap.values()) {

            List<OrderVo> orderVos = new ArrayList<>();
            //封装每一个订单
            for (Order order : orders) {
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order, orderVo);
                Product product = productMap.get(order.getProductId());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVos.add(orderVo);
            }
            result.add(orderVos);
        }

        R r = R.ok("订单数据获取成功!", result);
        log.info("OrderServiceImpl.list业务结束,结果:{}", result);
        return r;
    }

    /**
     * 后台管理查询订单数据
     *
     * @param pageParam
     * @return
     */
    @Override
    public R adminList(PageParam pageParam) {

        //分页参数计算完毕
        int offset = (pageParam.getCurrentPage() - 1) * pageParam.getPageSize();
        int pageSize = pageParam.getPageSize();
        List<AdminOrderVo> adminOrderVoList = orderMapper.selectAdminOrder(offset, pageSize);

        return R.ok("订单数据查询成功!", adminOrderVoList);
    }

    /**
     * 检查订单中是否有商品引用  影响删除
     *
     * @param productId
     * @return
     */
    @Override
    public R check(Integer productId) {

        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);

        Long count = orderMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("订单中有" + count + "件商品被占用,无法删除!");
        }
        return R.ok("订单中没有商品调用,可以删除!");
    }
}
