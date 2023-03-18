package com.imdemo.param;

import lombok.Data;

/**
 * @Time: 2022/11/30 21:27
 * @author: imdemo
 * description: 分页参数
 */
@Data
public class PageParam {
    //默认值 1
    private int currentPage = 1;
    //默认值 15
    private int pageSize = 15;
}
