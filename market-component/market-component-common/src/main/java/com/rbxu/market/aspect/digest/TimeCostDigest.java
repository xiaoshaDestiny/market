package com.rbxu.market.aspect.digest;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Aspect
@EnableAspectJAutoProxy
@Component
@Scope
@Slf4j
public class TimeCostDigest {

    private static final String LOG_SPLIT = "|";
    ArrayList<String> mappings = Lists.newArrayList("RequestMapping",
            "GetMapping",
            "PostMapping",
            "PutMapping",
            "DeleteMapping");

    @Pointcut(value = "@annotation(com.rbxu.market.aspect.digest.TimeCost) || @within(com.rbxu.market.aspect.digest.TimeCost)")
    public void timeCostDigest() {}


    @Around("timeCostDigest()")
    public Object logDigest(ProceedingJoinPoint pjb) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjb.getSignature();
        Method method = methodSignature.getMethod();
        String className = methodSignature.getMethod().getDeclaringClass().getSimpleName();
        String methodName = method.getName();
        //Annotation[] annotations = method.getDeclaredAnnotations();
        long invokeStart = System.currentTimeMillis();

        DigestInfo build = DigestInfo.builder()
                .startTime(String.valueOf(invokeStart))
                .className(className)
                .methodName(methodName)
                .build();

        Object result;
        try {
            result = pjb.proceed();
            build.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            build.setSuccess(Boolean.FALSE);
            throw e;
        } finally {
            build.setCost(String.valueOf(System.currentTimeMillis() - invokeStart));
            log.info(build.toLog());
        }
        return result;
    }



    @Data
    @Builder
    public static class DigestInfo {

        private String sourceIp;

        private String ip;

        private String className;

        private String methodName;

        private String cost;

        private String startTime;

        private Boolean success;


        public String toLog() {
            return LOG_SPLIT
                    + sourceIp + LOG_SPLIT
                    + ip + LOG_SPLIT
                    + className + LOG_SPLIT
                    + methodName + LOG_SPLIT
                    + cost + LOG_SPLIT
                    + success + LOG_SPLIT;
        }
    }
}
