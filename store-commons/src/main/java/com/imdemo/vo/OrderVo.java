package com.imdemo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.imdemo.pojo.Order;
import lombok.Data;

/**
 * @Time: 2022/12/3 14:30
 * @author: imdemo
 * description: 订单返回的数据实体
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderVo extends Order {

    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;
}
