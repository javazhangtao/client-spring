package com.rpc.suppor.spring;

import com.google.common.base.Strings;
import com.rpc.suppor.annotations.RPCClient;
import com.rpc.suppor.annotations.RPCServer;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * Created by zhangtao on 2015/12/15.
 * @RPCServer 注解类注入spring管理由注解name作为spring中bean唯一name
 */
public class RPCBeanNameGenerator extends AnnotationBeanNameGenerator {

    @Override
    protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
        AnnotationMetadata metadata = annotatedDef.getMetadata();
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(RPCServer.class.getName());
        if(null!=annotationAttributes && !annotationAttributes.isEmpty()) {
            return Strings.emptyToNull(annotationAttributes.get("name") + "");
        }
        Map<String,Object> clientAnnotation=metadata.getAnnotationAttributes(RPCClient.class.getName());
        if(null!=clientAnnotation && !clientAnnotation.isEmpty()){
            return Strings.emptyToNull(clientAnnotation.get("name") + "");
        }
        return super.determineBeanNameFromAnnotation(annotatedDef);
    }
}
