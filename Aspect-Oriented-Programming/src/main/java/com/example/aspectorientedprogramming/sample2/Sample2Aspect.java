package com.example.aspectorientedprogramming.sample2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(value = "sample2.enabled", havingValue = "true")
public class Sample2Aspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(Sample2Aspect.class);

    @Pointcut("execution(* com.example.aspectorientedprogramming.Machine.concat(..))")
    public void onConcat() {
    }

    @Around("onConcat() && args(a, b)")
    public String onConcat(ProceedingJoinPoint pjp, String a, String b) throws Throwable {
        LOGGER.info("The on concat method was called with arguments [{}] and [{}].", a, b);

        String modifiedA = "(" + a + ")";
        String modifiedB = "(" + b + ")";

        Object result = pjp.proceed(new Object[]{modifiedA, modifiedB});

        return "[" + result + "]";
    }
}
