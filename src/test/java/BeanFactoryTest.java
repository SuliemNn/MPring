import org.junit.Test;
import org.myspringframework.BeanDefinition;
import org.myspringframework.BeanFactory;

public class BeanFactoryTest {
    @Test
    public void testBeanFactory(){
        BeanFactory beanFactory = new BeanFactory();

        beanFactory.registerBeanDefinition("myString",new BeanDefinition(new String("1")));
        //预期输出：1
        System.out.println(beanFactory.getBean("myString"));
    }
}
