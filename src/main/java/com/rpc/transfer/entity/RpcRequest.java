package com.rpc.transfer.entity;

/**
 * Created by zhangtao on 2015/12/9.
 * 请求对象
 */
public class RpcRequest {

    String requestId;//请求Id
    String host;//请求服务地址
    Integer port;//请求服务端口
    String serverName;//请求服务名称
    String methondName;//请求服务方法名
    String serverVersion;//请求服务版本号
    Class<?>[] paramTypes;//请求方法参数类型
    Object[] params;//请求方法参数

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getMethondName() {
        return methondName;
    }

    public void setMethondName(String methondName) {
        this.methondName = methondName;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(String serverVersion) {
        this.serverVersion = serverVersion;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
