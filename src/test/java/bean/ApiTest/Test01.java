package bean.ApiTest;

import org.junit.Test;
import org.myspringframework.beans.factory.config.BeanDefinition;
import org.myspringframework.beans.factory.support.DefaultListableBeanFactory;

public class Test01 {
    @Test
    public void test_BeanFactory(){
        //流程概述：获取到Bean的class对象，之后获取到它的BeandeFinition，将BeanDefinition传入到beanFactory进行注册
        //此时的bean只是注册而没有实例化，当第一次获取之后才会进行实例化

        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2.注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.第一次获取 bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
        // 4.第二次获取 bean from Singleton
        UserService userService_singleton = (UserService) beanFactory.getBean("userService");
        userService_singleton.queryUserInfo();
    }
}
