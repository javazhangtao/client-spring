package com.rpc;

import com.rpc.suppor.transform.InterfaceProxy;
import com.server.UserServer;

/**
 * Created by zhangtao on 2015/12/17.
 */
public class MainTest {
    public static void main(String[] args) {
//        ClassWriter cw = new ClassWriter(0);

        try {
            InterfaceProxy proxy=new InterfaceProxy();
            UserServer server=(UserServer)proxy.create(UserServer.class);
            Object obj=server.getRequest("aaaa",123);
            System.out.println();
//            ApplicationContext context=new ClassPathXmlApplicationContext("/spring/context.xml");
//            UserServer userServer=(UserServer)context.getBean("userServer");
//            Object obj=userServer.getRequest("zzzz",123);
//            System.out.println();
//            InterfaceProxy proxy=new InterfaceProxy();
//            Object obj=proxy.createClass(UserService.class);
//            UserService service=(UserService)proxy.createClass(UserService.class);
//            Method m=UserService.class.getMethod("say",new Class<?>[]{String.class});
//            System.out.println(m.invoke(service,new Object[]{"123123"}));
//            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
