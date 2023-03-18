package com.imdemo.admin.inteceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Time: 2022/12/4 10:31
 * @author: imdemo
 * description: 登录保护拦截器
 * 进来的都是要拦截的
 * 检查session中是否有数据 userInfo
 * 有  放行  没有  跳转到登录页面
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Object userInfo = request.getSession().getAttribute("userInfo");

        if (userInfo != null) {
            return true;
        } else {
            response.sendRedirect(request.getContextPath() + "/index.html");
            return false;
        }
        //false 拦截   true  放行

    }
}
