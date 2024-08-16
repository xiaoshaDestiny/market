package com.rbxu.market.aspect;

import com.rbxu.market.constant.CommonConstant;
import com.rbxu.market.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@EnableAspectJAutoProxy
@Component
@Scope
@Slf4j
public class TimeCostAspect {

    @Pointcut(value = "@annotation(com.rbxu.market.aspect.TimeCost) || @within(com.rbxu.market.aspect.TimeCost)")
    public void timeCostDigest() {}


    @Around("timeCostDigest()")
    public Object logDigest(ProceedingJoinPoint pjb) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjb.getSignature();

        String className = methodSignature.getMethod().getDeclaringClass().getSimpleName();
        String methodName = methodSignature.getMethod().getName();
        long invokeStart = System.currentTimeMillis();
        TimeCost timeCost = getMethodOrElseClass(methodSignature);
        CostDigestDTO digest = CostDigestDTO.builder()
                .identify(Objects.isNull(timeCost) ? TimeCost.IDENTIFY_DEFAULT : timeCost.businessIdentify())
                .className(className)
                .methodName(methodName)
                .startTime(DateUtil.formatOrElseEmpty(invokeStart, CommonConstant.TIME_SECOND_FORMATTER))
                .build();
        Object result;
        try {
            result = pjb.proceed();
            digest.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            digest.setSuccess(Boolean.FALSE);
            throw e;
        } finally {
            long invokeEnd = System.currentTimeMillis();
            digest.setEndTime(DateUtil.formatOrElseEmpty(invokeEnd, CommonConstant.TIME_SECOND_FORMATTER));
            digest.setCost(invokeEnd - invokeStart);
            log.info(digest.toTimeCostLog());
        }
        return result;
    }

    private TimeCost getMethodOrElseClass(MethodSignature methodSignature) {
        if (Objects.isNull(methodSignature)) {
            return null;
        }
        TimeCost methodTimeCost = methodSignature.getMethod().getAnnotation(TimeCost.class);
        TimeCost classTimeCost = methodSignature.getMethod().getDeclaringClass().getAnnotation(TimeCost.class);
        return Objects.nonNull(methodTimeCost) ? methodTimeCost : classTimeCost;
    }

}
