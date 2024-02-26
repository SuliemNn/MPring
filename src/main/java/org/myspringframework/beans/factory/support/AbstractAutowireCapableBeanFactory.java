package org.myspringframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.myspringframework.beans.BeansException;
import org.myspringframework.beans.PropertyValue;
import org.myspringframework.beans.PropertyValues;
import org.myspringframework.beans.factory.config.AutowireCapableBeanFactory;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.config.BeanPostProcessor;
import org.myspringframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
    private InstantiationStrategy instantiationStrategy = new JDKInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        //通过Jdk动态代理来进行实例化
        try {
            bean = createBeanInstance(beanName,beanDefinition,args);
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 执行 Bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            bean = initializeBean(beanName, bean, beanDefinition);
        }catch (InstantiationException | IllegalAccessException e){
            throw new BeansException("Instantiation of bean failed", e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        //添加实例化好的bean对象到单例map中
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition,Object[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Constructor<?>[] declaredConstructors = beanDefinition.getBeanClass().getDeclaredConstructors();
        Constructor<?> constructorToUse = null;
        if (args != null) {
            //遍历每一个构造器，判断是否匹配
            for (Constructor<?> ctor : declaredConstructors) {
                if (ctor.getParameterTypes().length == args.length) {
                    boolean isMatch = true;
                    Class<?>[] parameterTypes = ctor.getParameterTypes();
                    for (int i = 0; i < args.length; i++) {
                        if (!parameterTypes[i].isAssignableFrom(args[i].getClass())) {
                            isMatch = false;
                            break;
                        }
                    }
                    if (isMatch) {
                        constructorToUse = ctor;
                        break;
                    }
                }
            }
        } else {
            //寻找是否有定义的无参构造
            for (Constructor<?> ctor : declaredConstructors) {
                if (ctor.getParameterCount() == 0) {
                    constructorToUse = ctor;
                    break;
                }
            }
        }

        if (constructorToUse == null) {
            //未找到匹配的构造器
            throw new BeansException("No matching constructor found for bean [" + beanName + "] of type [" + beanDefinition.getBeanClass().getName() + "]");
        }

        return instantiationStrategy.instantiate(beanDefinition,beanName,constructorToUse,args);
    }

    /**
     * Bean 属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {

                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B，获取 B 的实例化
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values：" + beanName);
        }
    }


    private Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        // 1. 执行 BeanPostProcessor Before 处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        // 待完成内容：invokeInitMethods(beanName, wrappedBean, beanDefinition);
        invokeInitMethods(beanName, wrappedBean, beanDefinition);

        // 2. 执行 BeanPostProcessor After 处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
        return wrappedBean;
    }

    private void invokeInitMethods(String beanName, Object wrappedBean, BeanDefinition beanDefinition) {

    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
    //设置Bean实例化策略
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (null == current) return result;
            result = current;
        }
        return result;
    }

}
