package com.imdemo.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Time: 2022/11/28 12:46
 * @author: imdemo
 * description:用户服务的启动类
 */
@MapperScan(basePackages = "com.imdemo.user.mapper")
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
