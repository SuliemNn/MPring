package org.myspringframework.beans.factory.config;

import org.myspringframework.beans.BeansException;

public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(org.myspringframework.beans.factory.ConfigurableListableBeanFactory beanFactory) throws BeansException;

}