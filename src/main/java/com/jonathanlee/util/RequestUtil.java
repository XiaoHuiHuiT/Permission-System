package com.jonathanlee.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

//    本地线程变量
    public static ThreadLocal<HttpServletRequest> local = new ThreadLocal();

//    获取
    public static HttpServletRequest getRequest(){
        return local.get();
    }

//    设置
    public static void setRequest(HttpServletRequest request){
        local.set(request);
    }

}