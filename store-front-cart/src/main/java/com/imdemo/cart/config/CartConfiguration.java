package com.imdemo.cart.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Time: 2022/12/3 13:10
 * @author: imdemo
 * description: 购物车模块的配置类
 */
@Configuration
public class CartConfiguration {

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
