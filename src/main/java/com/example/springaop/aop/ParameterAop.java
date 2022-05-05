package com.example.springaop.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


@Aspect
@Component
public class ParameterAop {

    /** 해당 aop를 적용시킬 범위 설정 - controller 하위의 모든 범위에 적용 **/
    @Pointcut("execution(* com.example.springaop.controller..*.*(..))")
    private void log() {}

    /** log() function이 실행되기 전에 수행되는 부분 **/
    @Before("log()")
    public void before(JoinPoint joinPoint){

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Object[] args = joinPoint.getArgs();
        for(Object obj : args) {
            System.out.println("type : " + obj.getClass().getSimpleName());
            System.out.println("value : " + obj);
        }

    }

    /** log() function 실행 후 값이 성공적으로 반환되면 수행되는 부분 **/
    @AfterReturning(value = "log()", returning="returnObj")
    public void afterReturn(JoinPoint joinPoint, Object returnObj){
        System.out.println("return object");
        System.out.println(returnObj);
    }

}
