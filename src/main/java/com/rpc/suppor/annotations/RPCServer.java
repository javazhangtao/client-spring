package com.rpc.suppor.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by zhangtao on 2015/12/9.
 * 对外接口类注解   spring 容器管理
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RPCServer{

    String name();//接口类名称
}
