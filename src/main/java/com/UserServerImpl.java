package com;

import com.server.UserServer;

/**
 * Created by zhangtao on 2015/12/24.
 */
public class UserServerImpl implements UserServer{
    @Override
    public Object getRequest(String name, Integer i) {
        System.out.println(name);
        return null;
    }
}
