package org.myspringframework.beans.factory.support;

import org.myspringframework.beans.BeansException;
import org.myspringframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

//通过Jdk动态代理实现含构造函数对象的实例化创建
public class JDKInstantiationStrategy implements InstantiationStrategy{

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz =  beanDefinition.getBeanClass();
        // 当构造函数存在的时候，使用构造函数进行构造


        try{
            if(ctor != null){
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            }else{
                //不存在时，使用默认的无参构造
                return clazz.getDeclaredConstructor().newInstance();
            }
        }catch (NoSuchMethodException| InvocationTargetException|  InstantiationException|  IllegalAccessException e){
            throw new BeansException("Faile to instantiate ["+clazz.getName()+"]",e);
        }
    }
}
