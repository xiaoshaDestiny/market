package com.rbxu.market.aspect;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.MessageFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CostDigestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * HTTP 日志格式
     */
    private static String HTTP_COST_LOG_FORMAT = "|{0}|{1}|{2}|{3}|{4}|{5}|{6}|{7}|{8}|{9}|{10}|";

    /**
     * 自定义 日志格式
     */
    private static String SELF_COST_LOG_FORMAT = "|{0}|{1}|{2}|{3}|{4}|{5}|{6}|";

    /**
     * 自定义业务标识
     */
    private String identify;

    /**
     * 请求IP
     */
    private String requestIp;

    /**
     * 处理IP
     */
    private String handleIp;

    /**
     * 目标类名
     */
    private String className;

    /**
     * 目标方法名称
     */
    private String methodName;

    /**
     * 请求URL
     */
    private String requestURL;

    /**
     * HTTP请求类型
     */
    private String requestType;

    /**
     * 处理开始时间
     */
    private String startTime;

    /**
     * 处理结束时间
     */
    private String endTime;

    /**
     * 耗时 ms
     */
    private Long cost;

    /**
     * 是否成功
     */
    private Boolean success;



    public String toHttpCostLog() {
        return MessageFormat.format(HTTP_COST_LOG_FORMAT, identify, className, methodName, requestIp, handleIp, requestURL, requestType, startTime, endTime, cost, success);
    }

    public String toTimeCostLog() {
        return MessageFormat.format(SELF_COST_LOG_FORMAT, identify, className, methodName, startTime, endTime, cost, success);
    }
}
