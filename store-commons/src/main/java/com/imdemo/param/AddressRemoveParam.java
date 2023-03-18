package com.imdemo.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Time: 2022/11/28 19:19
 * @author: imdemo
 * description: 地址移除参数实体
 */
@Data
public class AddressRemoveParam {
    @NotNull
    private Integer id;
}
