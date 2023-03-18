package com.imdemo.cart;

import com.imdemo.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Time: 2022/12/2 16:33
 * @author: imdemo
 * description: 购物车启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.imdemo.cart.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CartApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
