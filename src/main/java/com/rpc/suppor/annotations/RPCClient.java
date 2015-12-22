package com.rpc.suppor.annotations;

import java.lang.annotation.*;

/**
 * Created by zhangtao on 2015/12/17.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RPCClient {
    String name();
}
