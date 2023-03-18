package com.imdemo.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imdemo.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Time: 2022/12/3 15:36
 * @author: imdemo
 * description: 地址接收值的param
 */
@Data
public class AddressParam {

    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;
}
