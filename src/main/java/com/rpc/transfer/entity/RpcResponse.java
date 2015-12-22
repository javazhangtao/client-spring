package com.rpc.transfer.entity;

/**
 * Created by zhangtao on 2015/12/9.
 * 响应对象
 */
public class RpcResponse {
    String requestId;//请求id
    String message;//响应描述
    String code;//状态码
    Object data;//返回数据
    Throwable error;//错误信息

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
