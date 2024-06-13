package com.rbxu.market.aspect.digest;

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

    private String identify;

    private String requestIp;

    private String handleIp;

    private String className;

    private String methodName;

    private String requestURL;

    private String requestType;

    private String startTime;

    private String endTime;

    private Long cost;

    private Boolean success;

    private static String HTTP_COST_LOG_FORMAT = "|{0}|{1}|{2}|{3}|{4}|{5}|{6}|{7}|{8}|{9}|{10}|";

    public String toHttpCostLog() {
        return MessageFormat.format(HTTP_COST_LOG_FORMAT, identify, className, methodName, requestIp, handleIp, requestURL, requestType, startTime, endTime, cost, success);
    }

}
