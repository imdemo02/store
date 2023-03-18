package com.imdemo.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Time: 2022/11/29 18:48
 * @author: imdemo
 * description: 类别商品展示
 */
@Data
public class ProductIdsParam extends PageParam {
    @NotNull
    private List<Integer> categoryID;

}
