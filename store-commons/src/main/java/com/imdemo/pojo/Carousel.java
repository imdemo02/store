package com.imdemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Time: 2022/11/29 12:39
 * @author: imdemo
 * description: 轮播图实体类
 */
@Data
@TableName("carousel")
public class Carousel implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "carousel_id", type = IdType.AUTO)
    @JsonProperty("carousel_id")
    private Integer carouselId;
    @TableField("img_path")
    private String imgPath;
    private String describes;
    @TableField("product_id")
    @JsonProperty("product_id")
    private Integer productId;
    private Integer priority;
}
