package org.myspringframework.beans.factory.config;

import org.myspringframework.beans.PropertyValues;

public class BeanDefinition {
    //Bean对象不能通过Object直接创建，而应当先获取Class类，然后再在需要的时候创建bean对象
    private Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass(){
        return this.beanClass;
    }

    public void setBeanDefinition(Class beanClass){
        this.beanClass=beanClass;
    }

    public PropertyValues getPropertyValues() {
        return this.propertyValues;
    }
}
