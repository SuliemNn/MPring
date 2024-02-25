package Apitest;

import bean.UserDao;
import bean.UserService;
import org.junit.Test;
import org.myspringframework.beans.factory.support.DefaultListableBeanFactory;
import org.myspringframework.beans.factory.xml.XmlBeanDefinitionReader;

public class TestXml {
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. 获取Bean对象调用方法
        UserDao userDao = (UserDao) beanFactory.getBean("userDao") ;
        UserService userService = (UserService) beanFactory.getBean("userService");
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
