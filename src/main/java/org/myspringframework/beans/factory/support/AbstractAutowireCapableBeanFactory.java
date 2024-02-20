package org.myspringframework.beans.factory.support;

import org.myspringframework.beans.BeansException;
import org.myspringframework.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        //通过Class进行bean的实例化
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        //添加实例化好的bean对象到单例map中
        addSingleton(beanName, bean);
        return bean;
    }
}
