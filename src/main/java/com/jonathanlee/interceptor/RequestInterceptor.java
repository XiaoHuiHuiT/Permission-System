package com.jonathanlee.interceptor;

import com.jonathanlee.util.RequestUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 设置request
        RequestUtil.setRequest(request);
        return true;
    }

}