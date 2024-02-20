package org.myspringframework;

//Bean定义类，不对外提供
public class BeanDefinition {
    private Object bean;

    public BeanDefinition(Object bean){
        this.bean=bean;
    }

    public Object getBean(){
        return bean;
    }

}
