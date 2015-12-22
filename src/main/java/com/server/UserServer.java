package com.server;

import com.rpc.suppor.annotations.RPCClient;

/**
 * Created by zhangtao on 2015/12/22.
 */
@RPCClient(name="userServer")
public interface UserServer {

    public Object getRequest(String name , Integer i);
}
