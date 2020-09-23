package com.alexsoft.audit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class Auditing {

//    @Pointcut("execution(public * com.prices.services.ServiceParser.*(..))")
//    public void callAtMyServicePublic() { }
//
//    @Before("callAtMyServicePublic()")
//    public void beforeCallAtMethod1(JoinPoint jp) {
//        String args = Arrays.stream(jp.getArgs())
//                .map(a -> a.toString())
//                .collect(Collectors.joining(","));
//        log.info("before " + jp.toString() + ", args=[" + args + "]");
//    }
//
//    @After("callAtMyServicePublic()")
//    public void afterCallAt(JoinPoint jp) {
//        log.info("after " + jp.toString());
//    }

    //*******************************************************************
    // До перевода средств
//    @Before("execution(* com.prices.services.ServiceParser.*(..))")
//    public void getParsedItemsFromSite() {
//        System.out.println("getItemsFromSite  - ASPECT ");
//        log.info("ASPECT BEFORE");
//    }
//
//    // До перевода средств
//    @After("execution(* com.prices.services.ServiceParser.*(..))")
//    public void after() {
//        System.out.println("getItemsFromSite  - ASPECT ");
//        log.info("ASPECT AFTER");
//    }

    //*****************************************
    @Pointcut("within(com.alexsoft.services.ServiceParser)")
    public void stringProcessingMethods() {
    }

    @Before("stringProcessingMethods()")
    public void logMethodCall(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        log.info("название метода: " + methodName + " args: " + Arrays.toString(jp.getArgs()) + " // " + jp.getSignature());
    }

    @AfterReturning(pointcut = "execution(public * com.alexsoft.services.ServiceParser.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        String resulStr = "no result";
        if (result != null){
            resulStr = result.toString();
        }
        log.info("название метода: " + methodName + " возвращенное значение: " + resulStr);
    }

    @Around("@annotation(com.alexsoft.audit.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature().getName() + " выполнен за " + executionTime/1000 + "с");
        return proceed;
    }
}
