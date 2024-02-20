package org.myspringframework.beans.factory.config;

public class BeanDefinition {
    //Bean对象不能通过Object直接创建，而应当先获取Class类，然后再在需要的时候创建bean对象
    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass=beanClass;
    }

    public Class getBeanClass(){
        return this.beanClass;
    }

    public void setBeanDefinition(Class beanClass){
        this.beanClass=beanClass;
    }
}
