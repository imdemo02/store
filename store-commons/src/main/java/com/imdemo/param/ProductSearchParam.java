package com.imdemo.param;

import lombok.Data;

/**
 * @Time: 2022/11/30 21:28
 * @author: imdemo
 * description: 搜索关键字和分页参数
 */
@Data
public class ProductSearchParam extends PageParam {

    private String search;
}
