package com.bank.AccountAPI.Account.API.Project.Aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect()
@Component
public class LoggingAspect {

private static final Logger log =
        LoggerFactory.getLogger(LoggingAspect.class);

    @Around(value = "execution(* com.bank..*.*(..)) && @within(org.springframework.web.bind.annotation.RestController)")
public Object logDuration(ProceedingJoinPoint joinPoint) throws Throwable {
long startTime = System.currentTimeMillis();
Object result = joinPoint.proceed();
long endTime = System.currentTimeMillis();
log.info("Method {} took {} milliseconds" , joinPoint.getSignature().getName(), endTime - startTime);
        return result;
}
}