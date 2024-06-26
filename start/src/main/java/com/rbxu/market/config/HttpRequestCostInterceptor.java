package com.rbxu.market.config;

import com.rbxu.market.aspect.digest.dto.CostDigestDTO;
import com.rbxu.market.constant.CommonConstant;
import com.rbxu.market.util.DateUtil;
import com.rbxu.market.util.NetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Configuration
@Slf4j
public class HttpRequestCostInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> REQUEST_COST = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        REQUEST_COST.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            long endTime = System.currentTimeMillis();
            Long startTime = REQUEST_COST.get();
            Long cost = Objects.isNull(startTime) ? -1L : endTime - startTime;
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            String className = handlerMethod.getBeanType().getSimpleName();
            String requestType = request.getMethod();
            String methodName = handlerMethod.getMethod().getName();
            CostDigestDTO digest = CostDigestDTO.builder()
                    .identify("HTTP")
                    .className(className)
                    .methodName(methodName)
                    .requestIp(getRequestIpAddr(request))
                    .handleIp(NetUtil.currentIpOrElseEmpty())
                    .requestURL(request.getRequestURI())
                    .requestType(requestType)
                    .startTime(DateUtil.formatOrElseEmpty(REQUEST_COST.get(), CommonConstant.TIME_SECOND_FORMATTER))
                    .endTime(DateUtil.formatOrElseEmpty(System.currentTimeMillis(), CommonConstant.TIME_SECOND_FORMATTER))
                    .cost(cost)
                    .success(Objects.isNull(ex))
                    .build();
            log.info(digest.toHttpCostLog());
        } finally {
            REQUEST_COST.remove();
        }
    }

    private static String getRequestIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("0:0:0:0:0:0:0:1".equals(ipAddress)) {
                ipAddress = NetUtil.currentIpOrElseEmpty();
            }
        }
        return ipAddress;
    }
}
