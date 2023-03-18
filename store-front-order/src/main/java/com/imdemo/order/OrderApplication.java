package com.imdemo.order;

import com.imdemo.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Time: 2022/12/3 9:54
 * @author: imdemo
 * description: 订单模块的启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.imdemo.order.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
