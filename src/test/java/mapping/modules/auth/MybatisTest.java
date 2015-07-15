package mapping.modules.auth;

import nut.modules.auth.model.User;
import nut.modules.auth.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by root on 15-7-9.
 */
public class MybatisTest {

    private UserService userService;

    /**
     * 这个before方法在所有的测试方法之前执行，并且只执行一次
     * 所有做Junit单元测试时一些初始化工作可以在这个方法里面进行
     * 比如在before方法里面初始化ApplicationContext和userService
     */
    @Test
    public void before(){
        //使用"spring.xml"和"spring-context-mybatis.xml"这两个配置文件创建Spring上下文
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"spring-context-mybatis.xml"});
        //从Spring容器中根据bean的id取出我们要使用的userService对象
        userService = ac.getBean("userService",UserService.class);
//        String[] names = ac.getBeanDefinitionNames();
//        for (int i = 0; i < names.length; i++) {
//            System.out.println(names[i]);
//        }
    }

    @Test
    public void testAddUser(){
        User user = userService.select(1);
        System.out.println(user.getNAME());
//
        user = new User();
        user.setNAME("test");
        user.setAGE(23);
//        user.setID(23);
        userService.addUser(user);
        System.out.println(user.getID());
    }
}
