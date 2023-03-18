package com.imdemo.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imdemo.pojo.Order;
import com.imdemo.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Time: 2022/12/3 10:40
 * @author: imdemo
 * description:
 */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 查询点订单数据
     *
     * @param offset
     * @param pageSize
     * @return
     */
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset, @Param("pageSize") int pageSize);
}
