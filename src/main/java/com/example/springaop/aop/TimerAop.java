package com.example.springaop.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAop {

    /** bean은 함수 단위, Component는 클래스 단위로 등록 가능
     *  Configuration은 하나의 Class에 여러개의 Bean을 등록 가능
     * **/

    @Pointcut("execution(* com.example.springaop.controller..*.*(..))")
    private void log() {}

    @Pointcut("@annotation(com.example.springaop.annotation.Timer)")
    public void enableTimer(){}

    @Around("log() $$ enableTimer()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();

        stopWatch.stop();
        System.out.println("total time : " + stopWatch.getTotalTimeSeconds());

    }
}
