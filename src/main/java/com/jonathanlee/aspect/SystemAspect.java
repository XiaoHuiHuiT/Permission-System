package com.jonathanlee.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jonathanlee.domain.Systemlog;
import com.jonathanlee.mapper.SystemlogMapper;
import com.jonathanlee.util.RequestUtil;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class SystemAspect {

    @Autowired
    private SystemlogMapper systemlogMapper;

//    记录日志
    public void writeLog(JoinPoint joinPoint) throws JsonProcessingException {
        Systemlog systemlog = new Systemlog();

        // 设置时间
        systemlog.setOptime(new Date());
//        设置Ip地址，request添加拦截器，获取请求对象
        HttpServletRequest request = RequestUtil.getRequest();
        if(request != null){
            String Ip = request.getRemoteAddr();
            systemlog.setIp(Ip);
        }

//        方法
//        获取目标执行方法的全路径
        String name = joinPoint.getTarget().getClass().getName();
//        获取方法名称
        String signature = joinPoint.getSignature().getName();

        String func = name+":"+signature;
        systemlog.setFunction(func);

//        获取方法参数
        String params = new ObjectMapper().writeValueAsString(joinPoint.getArgs());
        systemlog.setParams(params);
        systemlogMapper.insert(systemlog);
    }

}