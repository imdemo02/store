package com.imdemo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Time: 2022/11/29 19:27
 * @author: imdemo
 * description: 商品id参数接收
 */
@Data
public class ProductIdParam {
    @NotNull
    private Integer productID;
}
