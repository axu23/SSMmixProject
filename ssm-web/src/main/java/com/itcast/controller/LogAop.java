package com.itcast.controller;

import com.itcast.domain.SysLog;
import com.itcast.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired//spring获取 绑定在当前访问请求线程上的request对象
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    private Date starTime;//开始访问的时间
    private Class clazz;//访问的类
    private String methodName;//访问类的方法名

    //前置通知 主要是获取开始时间，执行的类是哪一个，执行的是哪个方法
    @Before("execution(* com.itcast.controller.*.*(..))")
    public void BeforeLog(JoinPoint joinPoint) throws Exception {
        //JoinPoint这个对象是 spring帮我们封装的切入点
        starTime = new Date();//开始访问时间
        clazz = joinPoint.getTarget().getClass();//具体要访问的类
        methodName = joinPoint.getSignature().getName();//获取访问的方法的名称
    }

    //后置通知
    @AfterReturning("execution(* com.itcast.controller.*.*(..))")
    public void AfterLog(JoinPoint joinPoint) throws Exception {
        //判断的意思是：访问的访问的类不能是当前对象，
        if (clazz != LogAop.class && SysLogController.class != clazz) {
            //计算访问时间
            long time = new Date().getTime() - starTime.getTime();
            //获取访问路径
            String uri = request.getRequestURI();
            String contextPath = request.getContextPath();
            //将项目名替换掉
            uri.replace(contextPath, "");

            //获取请求用户的IP地址
            String ip = request.getRemoteAddr();

            //通过SpringSecity中Context获取用户名
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();

            /*开始封装SysLog*/
            SysLog sysLog = new SysLog();
            sysLog.setExecutionTime(time);
            sysLog.setIp(ip);
            sysLog.setMethod("类名：" + clazz + "方法名：" + methodName);
            sysLog.setUsername(username);
            sysLog.setUrl(uri);
            sysLog.setVisitTime(starTime);

            /*保存到数据库中*/
            sysLogService.save(sysLog);
        }
    }

}
