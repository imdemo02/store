package com.imdemo.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @Time: 2022/11/29 17:08
 * @author: imdemo
 * description: 热门商品参数接受对象
 */
@Data
public class ProductHotParam {

    @NotEmpty
    private List<String> categoryName;
}
