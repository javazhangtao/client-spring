package com.rpc;

import com.server.UserServer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.lang.reflect.Method;

/**
 * Created by zhangtao on 2015/12/17.
 */
public class MainTest {
    public static void main(String[] args) {
//        ClassWriter cw = new ClassWriter(0);

        try {
            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            String interfaceClass= UserServer.class.getName().replaceAll("\\.", "/");
            String className=interfaceClass.replaceAll(UserServer.class.getSimpleName(),"impl/"+UserServer.class.getSimpleName()+"Impl");
            cw.visit(Opcodes.V1_7, Opcodes.ACC_PUBLIC + Opcodes.ACC_SUPER, "com/server/impl/UserServerImpl", null, "java/lang/Object", new String[]{interfaceClass});
            MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD,0);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Object","<init>","()V",true);
            mv.visitInsn(Opcodes.RETURN);
//            mv.visitMaxs(0,0);
            mv.visitEnd();
            Method[] methods=UserServer.class.getMethods();
            for (Method method:methods){
                mv=cw.visitMethod(Opcodes.ACC_PUBLIC,method.getName(), Type.getMethodDescriptor(method),null,null);
                mv.visitCode();
                mv.visitVarInsn(Opcodes.ALOAD,1);
                mv.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/System","out","Ljava/io/PrintStream;");
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,"java/io/PrintStream","println","(Ljava/lang/String;)V",true);
                mv.visitInsn(Opcodes.ACONST_NULL);
                mv.visitInsn(Opcodes.ARETURN);
                mv.visitMaxs(0,0);
                mv.visitEnd();
            }
            cw.visitEnd();
            byte[] code=cw.toByteArray();
            ClientLoader loader=new ClientLoader();
            Class clazz=loader.defineClassByName("com.server.impl.UserServerImpl",code,0,code.length);
            UserServer server=(UserServer)clazz.getConstructor(null).newInstance(null);
            System.out.println();
//            ClassReader reader=new ClassReader("com.server.impl.UserServerImpl");
//            byte[] code=reader.b;
////            for (int i=0;i<reader.header;i++){
////                code[i]=
////            }
//            System.out.print(new String(code));
//            String className="UserServer"+"$Impl";);
//            String interfaceClass= UserServer.class.getName().replaceAll("\\.", "/");
//            ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//            cw.visit(Opcodes.V1_7,Opcodes.ACC_PUBLIC,className,null,"java/lang/Object",new String[]{interfaceClass});
//            MethodVisitor mv=cw.visitMethod(Opcodes.ACC_PUBLIC,"<init>","()V",null,null);
//            mv.visitVarInsn(Opcodes.ALOAD,0);
//            mv.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Object","<init>","()V",false);
//            mv.visitInsn(Opcodes.RETURN);
//            mv.visitMaxs(1, 1);
//            mv.visitEnd();
//            Method[] methods=UserServer.class.getMethods();
//            for (Method method:methods){
//                mv=cw.visitMethod(Opcodes.ACC_PUBLIC,method.getName(), Type.getMethodDescriptor(method),null,null);
//                mv.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Object",method.getName(),Type.getMethodDescriptor(method),false);
//                mv.visitFieldInsn(Opcodes.GETSTATIC,"java/lang/Object",);
//                mv.visitInsn(Opcodes.RETURN);
//                mv.visitMaxs(2, 1);
//                mv.visitEnd();
//            }
//            cw.visitEnd();
//            byte[] code = cw.toByteArray();
//            ClientLoader loader=new ClientLoader();
//            Class clazz=loader.defineClassByName(className,code,0,code.length);
//            UserServer userServer=(UserServer)clazz.getConstructor().newInstance();
//            System.out.println();
//            Enhancer enhancer=new Enhancer();
//            enhancer.setSuperclass(UserServer.class);
//            enhancer.setCallback(new MyProxy());
////            enhancer.setCallbackType(MyProxy.class);
//            UserServer server=(UserServer)enhancer.create(new Class[],new );
//            System.out.println();
//            InterfaceProxy proxy=new InterfaceProxy();
//            UserServer server=(UserServer)proxy.create(UserServer.class);
//            Object obj=server.getRequest("aaaa",123);
//            System.out.println();
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

    static class MyProxy implements MethodInterceptor{
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("123");
            return null;
        }
    }

    static class ClientLoader extends ClassLoader{
        @SuppressWarnings("unchecked")
        public  Class defineClassByName(String name,byte[] b,int off,int len){
            return super.defineClass(name,b, off, len);
        }
    }
}
