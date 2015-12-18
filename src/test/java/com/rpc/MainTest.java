package com.rpc;

import com.rpc.service.UserService;
import com.rpc.suppor.transform.InterfaceProxy;

import java.lang.reflect.Method;

/**
 * Created by zhangtao on 2015/12/17.
 */
public class MainTest {
    public static void main(String[] args) {
//        ClassWriter cw = new ClassWriter(0);

        try {
            InterfaceProxy proxy=new InterfaceProxy();
            UserService service=(UserService)proxy.createClass(UserService.class);
            Method m=UserService.class.getMethod("say",new Class<?>[]{String.class});
            System.out.println(m.invoke(service,new Object[]{"123123"}));
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
