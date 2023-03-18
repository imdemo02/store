package com.imdemo.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Time: 2022/12/3 10:28
 * @author: imdemo
 * description: 订单发送商品服务的实体
 */
@Data
public class OrderToProduct implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer productId;

    private Integer num;

}
