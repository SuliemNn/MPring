package org.myspringframework.beans.factory;

import org.myspringframework.beans.BeansException;

public interface BeanFactory {
    Object getBean(String name) throws BeansException;
}
