package Processor;

import org.myspringframework.beans.BeansException;
import org.myspringframework.beans.PropertyValue;
import org.myspringframework.beans.PropertyValues;
import org.myspringframework.beans.factory.ConfigurableListableBeanFactory;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.config.BeanFactoryPostProcessor;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }

}