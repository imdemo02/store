package com.imdemo.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imdemo.vo.CartVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Time: 2022/12/3 10:25
 * @author: imdemo
 * description: 订单接受参数param
 */
@Data
public class OrderParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;

    private List<CartVo> products;
}
