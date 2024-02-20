package org.myspringframework.beans.factory.support;

import org.myspringframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    //这个Map用来存储实例化好了的Bean对象
    private Map<String, Object> singletonObjects = new HashMap<>();

    //获取单例bean对象
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    //将单例Bean对象添加到单列map中
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
