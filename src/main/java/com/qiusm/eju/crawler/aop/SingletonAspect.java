package com.qiusm.eju.crawler.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 因为爬虫的一些调用，必须是单线程启动的，如果已经存在运行了，则需要等待当前的运行结束，所以增加切面进行控制。<br>
 *
 * @author qiushengming
 */
@Aspect
@Component
public class SingletonAspect {

    /**
     * 正在执行的方法
     */
    private static final Set<String> METHOD_BEING_EXECUTED = new HashSet<>();

    /**
     * 定义切入点
     */
    @Pointcut("execution(public * com.qiusm.eju.crawler..*Service.*Start(..))")
    public void pointcut() {
    }

    /**
     * 环绕通知 ===> 正常情况执行在@Before和@After之前,如果执行过程中抛异常,只执行前置环绕通知,后置环绕不执行
     * <code>@Around(value = "pointcut()")</code>
     *
     * @param point 切点
     * @return 返回值
     */
    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object proceed = null;
        String key = null;
        synchronized (SingletonAspect.class) {
            Signature signature = point.getSignature();
            String methodName = signature.getName();
            String className = signature.getDeclaringTypeName();
            key = className + "." + methodName;
            if (METHOD_BEING_EXECUTED.contains(key)) {
                return null;
            } else {
                METHOD_BEING_EXECUTED.add(key);
            }
        }

        try {
            proceed = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        METHOD_BEING_EXECUTED.remove(key);

        return proceed;
    }
}
