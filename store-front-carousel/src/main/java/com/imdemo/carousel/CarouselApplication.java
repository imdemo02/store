package com.imdemo.carousel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


/**
 * @Time: 2022/11/29 12:06
 * @author: imdemo
 * description: 轮播图启动类
 */
@SpringBootApplication
@MapperScan(basePackages = "com.imdemo.carousel.mapper")
@EnableCaching
public class CarouselApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarouselApplication.class, args);
    }

}
