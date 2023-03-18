package com.imdemo.collect;

import com.imdemo.clients.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Time: 2022/12/2 13:37
 * @author: imdemo
 * description: 收藏模块的启动类
 */
@SpringBootApplication
@MapperScan(value = "com.imdemo.collect.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class CollectApplication {
    public static void main(String[] args) {
        SpringApplication.run(CollectApplication.class, args);
    }
}
