package com.imdemo.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Time: 2022/11/29 14:28
 * @author: imdemo
 * description: 类别热门商品参数接受
 */
@Data
public class ProductPromoParam {

    @NotBlank
    private String categoryName;
}
