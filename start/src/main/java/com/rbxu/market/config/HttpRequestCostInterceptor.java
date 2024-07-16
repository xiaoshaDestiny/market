package com.rbxu.market.config;

import com.rbxu.market.aspect.digest.dto.CostDigestDTO;
import com.rbxu.market.constant.CommonConstant;
import com.rbxu.market.util.DateUtil;
import com.rbxu.market.util.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Configuration
@Slf4j
public class HttpRequestCostInterceptor implements HandlerInterceptor {
    private static final String HTTP_IDENTIFY = "HTTP";
    private static final Long ERROR_COST = -1L;

    private static final ThreadLocal<Long> REQUEST_START_TIMESTAMP = new ThreadLocal<>();

    @Override
    public boolean preHandle(@NotNull HttpServletRequest request,
                             @NotNull HttpServletResponse response,
                             @NotNull Object handler) throws Exception {
        REQUEST_START_TIMESTAMP.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request,
                                @NotNull HttpServletResponse response,
                                @NotNull Object handler,
                                Exception ex) throws Exception {
        try {
            long endTime = System.currentTimeMillis();
            Long startTime = REQUEST_START_TIMESTAMP.get();
            Long cost = Objects.isNull(startTime) ? ERROR_COST : endTime - startTime;
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                String className = handlerMethod.getBeanType().getSimpleName();
                String requestType = request.getMethod();
                String methodName = handlerMethod.getMethod().getName();
                CostDigestDTO digest = CostDigestDTO.builder()
                        .identify(HTTP_IDENTIFY)
                        .className(className)
                        .methodName(methodName)
                        .requestIp(NetUtil.getRequestIpAddr(request))
                        .handleIp(NetUtil.currentIpOrElseEmpty())
                        .requestURL(request.getRequestURI())
                        .requestType(requestType)
                        .startTime(DateUtil.formatOrElseEmpty(REQUEST_START_TIMESTAMP.get(), CommonConstant.TIME_SECOND_FORMATTER))
                        .endTime(DateUtil.formatOrElseEmpty(System.currentTimeMillis(), CommonConstant.TIME_SECOND_FORMATTER))
                        .cost(cost)
                        .success(Objects.isNull(ex))
                        .build();
                log.info(digest.toHttpCostLog());
            }
        } finally {
            REQUEST_START_TIMESTAMP.remove();
        }
    }
}
