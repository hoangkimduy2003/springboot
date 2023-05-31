package com.project2.aop;

import com.project2.dto.DepartmentDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAOP {

    @Autowired
    private CacheManager cacheManager;

    @AfterReturning(value = "execution(* com.project2.service.IDepartmentService.findById(*))",
            returning = "returnValue")
    public void getDepartmentId(JoinPoint joinPoint, Object returnValue) {
        int id = (Integer) joinPoint.getArgs()[0];
        Object[] o = joinPoint.getArgs();
        log.info("JOIN POINT: " + id);
        DepartmentDTO x = (DepartmentDTO) returnValue;
        log.info(x.getName());
    }
}
