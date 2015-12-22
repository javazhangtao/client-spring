package com.rpc.suppor.transform;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by zhangtao on 2015/12/22.
 */
public class CGLIBProxyFactory<T> implements FactoryBean<T>{

    private Class<T> sourceInterface;

    @Override
    public T getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public Class<T> getSourceInterface() {
        return sourceInterface;
    }

    public void setSourceInterface(Class<T> sourceInterface) {
        this.sourceInterface = sourceInterface;
    }
}
