package com.rbxu.market.enums;

public enum ErrorCodeEnum {
    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常!"),
    PARAM_IS_NULL("PARAM_IS_NULL", "参数为空!"),

    GET_RESULT_FROM_OTHER_SERVICE_IS_NULL("GET_RESULT_FROM_OTHER_SERVICE_IS_NULL", "调用服务{0}返回结果为空!");


    private final String errCode;
    private final String errDesc;

    ErrorCodeEnum(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrDesc() {
        return errDesc;
    }
}
