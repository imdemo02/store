package com.imdemo.product.config;

import com.imdemo.config.CacheConfiguration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time: 2022/12/1 18:12
 * @author: imdemo
 * description: 商品模块的配置类
 */
@Configuration
public class ProductConfiguration extends CacheConfiguration {

    /**
     * mq 序列化方式 选择json
     *
     * @return
     */
    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }
}
