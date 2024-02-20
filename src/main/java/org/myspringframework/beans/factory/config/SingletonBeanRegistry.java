package org.myspringframework.beans.factory.config;

public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
