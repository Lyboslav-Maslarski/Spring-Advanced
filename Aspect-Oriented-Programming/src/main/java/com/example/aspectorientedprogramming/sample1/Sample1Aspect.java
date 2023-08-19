package com.example.aspectorientedprogramming.sample1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@ConditionalOnProperty(value = "sample1.enabled", havingValue = "true")
public class Sample1Aspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample1Aspect.class);

    @Pointcut("execution(* com.example.aspectorientedprogramming.Machine.*(..))")
    public void onAllMachineMethods() {
    }

    @Pointcut("execution(* com.example.aspectorientedprogramming.Machine.echo(..))")
    public void onEchoCalled() {
    }

    @Before("onAllMachineMethods()")
    public void beforeEachMethod(JoinPoint joinPoint) {
        LOGGER.info("Before calling method {} with arguments {}",
                joinPoint.getSignature(), Arrays.asList(joinPoint.getArgs()));
    }

    @AfterThrowing(pointcut = "execution(* com.example.aspectorientedprogramming.Machine.boom())", throwing = "throwable")
    public void afterTrowing(JoinPoint joinPoint, Throwable throwable) {
        LOGGER.error("Ups, I think that the method {} threw exception and the exceptions is...",
                joinPoint.getSignature(), throwable);
    }
}
