package org.myspringframework.beans.factory.support;

import org.myspringframework.beans.BeansException;
import org.myspringframework.beans.factory.BeanFactory;
import org.myspringframework.beans.factory.config.BeanDefinition;

//模板模式，不做具体的实现
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }

        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}