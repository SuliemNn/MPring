package org.myspringframework.beans.factory.support;

import org.myspringframework.beans.factory.config.BeanDefinition;

//Bean注册
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}