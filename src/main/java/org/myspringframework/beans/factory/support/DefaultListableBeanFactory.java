package org.myspringframework.beans.factory.support;

import org.myspringframework.beans.BeansException;
import org.myspringframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;


public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    //管理注册好的BeanDefinition
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    //进行Bean的注册
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

}