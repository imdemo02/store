package com.imdemo.admin;

import com.imdemo.clients.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Time: 2022/12/3 18:31
 * @author: imdemo
 * description: 启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.imdemo.admin.mapper")
@EnableCaching
@EnableFeignClients(clients = {UserClient.class, CategoryClient.class, SearchClient.class, OrderClient.class, ProductClient.class})
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
