package com.imdemo.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Time: 2022/12/2 19:00
 * @author: imdemo
 * description: 购物车添加参数接收
 */
@Data
public class CartSaveParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
    @NotNull
    @JsonProperty("product_id")
    private Integer productId;
}
