package com.rpc.suppor.transform;


import com.rpc.suppor.annotations.RPCClient;
import com.rpc.transfer.entity.RpcRequest;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by zhangtao on 2015/12/17.
 * 生成接口实现类，并返回交由spring容器管理
 */
public class InterfaceProxy implements MethodInterceptor {
    /**
     * 创建接口实现类 ， 并返回对象
     * @param clazz
     * @return
     * @throws Exception
     */
    public Object create(Class<?> clazz) throws Exception{
        if(null!=clazz) {
            return createClass(clazz);
        }else {
            throw new NullPointerException();
        }
    }


    /**
     * 创建动态实现类
     * @param interfaceClass
     * @return
     * @throws Exception
     */
    private Object createClass(final Class<?> interfaceClass) throws Exception{
        try {
            Enhancer enhancer=new Enhancer();
            enhancer.setSuperclass(interfaceClass);//设置实现接口
            enhancer.setCallback(this);//设置回调函数
            return enhancer.createClass();//创建代理对象
        } catch (Exception e) {
            throw(e);
        }
    }

    /**
     * 方法执行
     * @param obj
     * @param method
     * @param args
     * @param proxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        RPCClient client= obj.getClass().getAnnotation(RPCClient.class);
        RpcRequest request=new RpcRequest();
        if(null!=client){
            request.setServerName(client.name());
        }
        request.setMethondName(method.getName());
        request.setParamTypes(method.getParameterTypes());
        request.setParams(args);
        request.setRequestId(UUID.randomUUID().toString());
        return request;
    }

//    private void createMethodProxy(final ClassWriter cw , final Method method){
//        MethodVisitor mv=cw.visitMethod(V1_7,method.getName(),Type.getMethodDescriptor(method),null,null);
//    }

//    /**
//     * 创建方法
//     * @param cw
//     * @param method
//     */
//    private void createMethod(final ClassWriter cw,final Method method){
//        MethodVisitor mv=cw.visitMethod(ACC_PUBLIC,method.getName(),Type.getMethodDescriptor(method),null,null);
//        mv.visitCode();
////        mv.visitVarInsn(ALOAD,0);
//        mv.visitMethodInsn(INVOKEINTERFACE,"java/lang/Object",method.getName(),Type.getMethodDescriptor(method),true);
//        mv.visitInsn(RETURN);
//        mv.visitEnd();
//    }

//    /**
//     * 创建无参构造函数
//     * @param cw
//     */
//    private void createStructure(final ClassWriter cw){
//        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,null);//修饰符，方法名，方法签名（返回值），参数，异常
//        mv.visitCode();
////        mv.visitVarInsn(ALOAD, 0);
//        mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/Object", "<init>","()V" ,true);
//        mv.visitInsn(RETURN);
//        mv.visitEnd();
//    }


//    class ClientLoader extends ClassLoader{
//        @SuppressWarnings("unchecked")
//        public  Class defineClassByName(String name,byte[] b,int off,int len){
//            return super.defineClass(name,b, off, len);
//        }
//    }
}
