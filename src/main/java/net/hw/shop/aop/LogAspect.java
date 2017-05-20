package net.hw.shop.aop;

import net.hw.shop.bean.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

//import net.hw.shop.service.LogService;

/**
 * Created by howard on 2017/5/5.
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
//    private LogService logService;

    // 注解声明切点
    @Pointcut("@annotation(net.hw.shop.aop.Action)")
    public void mypt() {}

    // 注解声明前置建言，并使用@PointCut定义的切点
    @Before("mypt()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);

        String username = (String) request.getSession().getAttribute("username");
        String url = request.getRequestURL().toString();
        Date time = new Date();
        String operation = method.getName() + "方法准备执行";

        Log log = new Log();
        log.setUsername(username == null ? "未登录用户" : username);
        log.setUrl(url);
        log.setTime(time);
        log.setOperation(operation);
//        logService.addLog(log);
    }


    // 注解声明后置建言，并使用@PointCut定义的切点
    @After("mypt()")
    public void after(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);

        String username = (String) request.getSession().getAttribute("username");
        String url = request.getRequestURL().toString();
        Date time = new Date();
        String operation = method.getName() + "方法执行完毕";

        Log log = new Log();
        log.setUsername(username == null ? "未登录用户" : username);
        log.setUrl(url);
        log.setTime(time);
        log.setOperation(operation);
//        logService.addLog(log);
    }
}
