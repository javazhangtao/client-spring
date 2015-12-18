package com.rpc.suppor.transform;


import org.apache.log4j.Logger;
import org.objectweb.asm.*;
import org.objectweb.asm.Type;

import java.lang.reflect.*;

/**
 * Created by zhangtao on 2015/12/17.
 */
public class InterfaceProxy extends ClassLoader implements Opcodes {

    Logger logger=Logger.getLogger(InterfaceProxy.class);

    public Object createClass(Class<?> clazz) throws Exception{
        String interfactName= Type.getDescriptor(clazz).substring(1,Type.getDescriptor(clazz).length()-1);
        String implSimpleName=clazz.getSimpleName()+"$Impl";
        String implName=interfactName.replace(clazz.getSimpleName(),implSimpleName);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cw.visit(V1_7,ACC_PUBLIC,implName,null,"java/lang/Object",new String[] {interfactName});
        createStructure(cw);//创建无参构造
        Method[] methods=clazz.getMethods();
        for (Method method:methods){
            createMethod(cw,method);
        }
        cw.visitEnd();
        byte[] code = cw.toByteArray();
        InterfaceProxy loader = new InterfaceProxy();
        Class<?> _clazz=loader.defineClass("com.rpc.service.UserService$Impl",code,0,code.length);
        return _clazz.getConstructor().newInstance();
    }

    /**
     * 创建方法
     * @param cw
     * @param method
     */
    private void createMethod(final ClassWriter cw,final Method method){
        MethodVisitor mv=cw.visitMethod(V1_7,method.getName(),Type.getMethodDescriptor(method),null,null);
        mv.visitCode();
        mv.visitVarInsn(ALOAD,0);
        mv.visitMethodInsn(INVOKEINTERFACE,"java/lang/Object",method.getName(),Type.getMethodDescriptor(method),false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(1, 1);
        mv.visitEnd();
    }

    /**
     * 创建无参构造函数
     * @param cw
     */
    private void createStructure(final ClassWriter cw){
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null,null);//修饰符，方法名，方法签名（返回值），参数，异常
        mv.visitCode();
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKEINTERFACE, "java/lang/Object", "<init>","()V" ,true);
        mv.visitInsn(RETURN);
        mv.visitEnd();
    }
}
