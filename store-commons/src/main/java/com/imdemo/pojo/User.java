package com.imdemo.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Time: 2022/11/28 13:05
 * @author: imdemo
 * description: 用户实体类
 */
@Data
//指定数据库对应的表
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    //指定主键自增长
    @TableId(type = IdType.AUTO)
    //jackson的注解，用于进行属性格式化 接收和生成json时 用user_id
    @JsonProperty("user_id")
    private Integer userId;
    @Length(min = 6)
    private String userName;
    @NotBlank
    //忽略属性，不生成json 不接受json数据  @JsonIgnore
    //JsonInclude(JsonInclude.Include.NON_NULL)  当这个只不为null的时候生成json，为null不生成
    //不影响接受json
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @NotBlank
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPhonenumber;
}
