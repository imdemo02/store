package com.imdemo.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Time: 2022/12/2 21:14
 * @author: imdemo
 * description: 购物车查询接受参数
 */
@Data
public class CartListParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;
}
