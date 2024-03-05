package com.rbxu.market.dto;

import com.alibaba.cola.dto.SingleResponse;

import java.io.Serializable;
public class RpcResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data;

    private boolean success;
    private String errCode;
    private String errMessage;

    public RpcResponse() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public static RpcResponse buildFailure(String errCode, String errMessage) {
        RpcResponse response = new RpcResponse();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        response.setData(null);
        return response;
    }

    public static <T> RpcResponse<T> of(T data) {
        RpcResponse<T> response = new RpcResponse();
        response.setSuccess(true);
        response.setData(data);
        return response;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return this.errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String toString() {
        return "Response [success=" + this.success + ", errCode=" + this.errCode + ", errMessage=" + this.errMessage + "]";
    }
}