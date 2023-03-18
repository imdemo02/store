package com.imdemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Time: 2022/11/29 14:32
 * @author: imdemo
 * description: 商品实体类
 */
@Data
@TableName("product")
@AllArgsConstructor
@NoArgsConstructor
//TODO:修改product实体类，添加忽略没有属性的注解
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("product_id")
    private Integer productId;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("category_id")
    private String categoryId;

    /**
     * 手机title
     */
    @JsonProperty("product_title")
    private String productTitle;

    /**
     * 手机信息描述
     */
    @JsonProperty("product_intro")
    private String productIntro;

    @JsonProperty("product_picture")
    private String productPicture;

    /**
     * 商品价格
     */
    @JsonProperty("product_price")
    private Double productPrice;

    /**
     * 售卖价格
     */
    @JsonProperty("product_selling_price")
    private Double productSellingPrice;

    /**
     * 商品库存
     */
    @JsonProperty("product_num")
    private int productNum;

    /**
     * 已卖数量
     */
    @JsonProperty("product_sales")
    private int productSales;

}
