package org.myspringframework.beans.factory;

import org.myspringframework.beans.BeansException;
import org.myspringframework.beans.factory.config.AutowireCapableBeanFactory;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.config.BeanPostProcessor;
import org.myspringframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Configuration interface to be implemented by most listable bean factories.
 * In addition to {@link ConfigurableBeanFactory}, it provides facilities to
 * analyze and modify bean definitions, and to pre-instantiate singletons.
 *
 *
 *
 *
 *
 *
 * 作者：DerekYRC https://github.com/DerekYRC/mini-spring
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

}
