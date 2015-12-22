package com.rpc.suppor.spring;

import com.rpc.suppor.transform.InterfaceProxy;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Set;

/**
 * Created by zhangtao on 2015/12/22.
 */
public final class RPCScanner extends ClassPathBeanDefinitionScanner {

    Logger logger=Logger.getLogger(RPCScanner.class);

    private Class<? extends Annotation> annotationClass;//注解

    public RPCScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    /**
     * 添加扫描默认过滤器
     */
    @Override
    protected void registerDefaultFilters() {
        boolean acceptAllInterfaces = true;
        if(null!=this.annotationClass){
            addIncludeFilter(new AnnotationTypeFilter(this.annotationClass));
            acceptAllInterfaces = false;
        }
        if (acceptAllInterfaces) {
            addIncludeFilter(new TypeFilter() {
                public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                    return true;
                }
            });
        }
        addExcludeFilter(new TypeFilter() {
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
                String className = metadataReader.getClassMetadata().getClassName();
                return className.endsWith("package-info");
            }
        });
    }

    /**
     * spring 扫描主程序
     * @param basePackages
     * @return
     */
    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);
        if(beanDefinitions.isEmpty()){
            logger.warn("not have rpc's bean found");
        }else{
            try {
                for (BeanDefinitionHolder holder:beanDefinitions){
                    GenericBeanDefinition definition=(GenericBeanDefinition)holder.getBeanDefinition();//获取spring实例对象bean
                    definition.setScope("prototype");//设置spring管理bean为多例
                    InterfaceProxy proxy=new InterfaceProxy();
                    Object obj=proxy.create(Class.forName(definition.getBeanClassName()));
                    definition.setBeanClass(obj.getClass());
                    System.out.println();
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return beanDefinitions;
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return (beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent());
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> annotationClass) {
        this.annotationClass = annotationClass;
    }
}
