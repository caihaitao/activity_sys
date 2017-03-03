package com.cc.base;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xn032607 on 2017/1/10.
 */
@Aspect
public class MethodTimeCount {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.cc.service.*.*(..))")
    public void timeCount() {}

    @Around("timeCount()")
    public Object proceed(ProceedingJoinPoint joinPoint) {
        Object object = null;
        try {
            long t1 = System.currentTimeMillis();
            object = joinPoint.proceed();
            long t2 = System.currentTimeMillis();
            String className = joinPoint.getTarget().getClass().getName();
            MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
            String methodName = methodSignature.getMethod().getName();

            if(logger.isInfoEnabled()) {
                logger.info("Class-{},Method-{} cost {} ms",className,methodName,(t2-t1));
            }
        } catch (Throwable throwable) {
            if(logger.isErrorEnabled()) {
                logger.error("invoke timeCount error -{}",throwable.getCause());
            }
        }

        return object;
    }
}
