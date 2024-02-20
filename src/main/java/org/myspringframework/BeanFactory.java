package org.myspringframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Bean工厂：用于创建和添加Bean对象
public class BeanFactory {

    //使用ConcurrentHashMap是为了避免多线程下的并发安全问题
    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String beanName){
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        return beanDefinition.getBean();
    }

    //注册Bean
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition){
        beanDefinitionMap.put(name,beanDefinition);
    }

}
