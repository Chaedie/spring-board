package com.example.springbootboard.global.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ControllerExecutionTimeAOP {

    @Around("@annotation(TimeClock)")
    // @Around("execution(* com.example.springbootboard.controller.*.*.*(..))")
    public Object executionAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Object result = joinPoint.proceed();

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        return result;
    }
}
