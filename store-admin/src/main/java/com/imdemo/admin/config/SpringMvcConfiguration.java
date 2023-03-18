package com.imdemo.admin.config;

import com.imdemo.admin.inteceptors.LoginProtectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Time: 2022/12/4 10:41
 * @author: imdemo
 * description: SpringMvc的配置类
 */
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    /**
     * 用户注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截后台管理模块的路径  排除登录和资源路径
        registry.addInterceptor(new LoginProtectInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/index.html", "/index", "/static/**",
                        "/user/login", "/user/logout",
                        "/api/**", "/css/**", "/images/**",
                        "/js/**", "/lib/**", "/captcha"
                );
    }

}
