package sample;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;


/**
 * Created by root on 15-7-9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = {"classpath:spring-context-cache.xml","classpath:spring-context-mybatis.xml","classpath:spring-context-shiro.xml","classpath:spring-mvc.xml", "classpath:spring-sample-mvc.xml"}),
//        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc*.xml")
})
public class UserService2Test {

    public static final Log logger = LogFactory.getFactory().getInstance(UserService2Test.class);
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private UserServiceImpl2 userService;
    @Autowired
    private UserMapper2<User,Integer> userMapper2;
    @Autowired(required = false)
    private SqlSession sqlSession;

    @Before
    public void before(){
        printwac(wac);
//        userMapper = wac.getBean(UserMapper.class);
//        System.out.println(userMapper);
//        Assert.assertNotNull(userService);
    }

    private void printwac(WebApplicationContext wac){
        String[] dn = wac.getBeanNamesForType(SqlSession.class);
        for (int i = 0; i < dn.length; i++) {
            System.out.println(dn[i]);
        }
    }

    @Test
    public void testSelect(){

        User user = userService.select(1);
        System.out.println(user.getNAME());
//
//        user = new User();
//        user.setNAME("test");
//        user.setAGE(23);
//        user.setID(23);
//        userService.addUser(user);
//        System.out.println(user.getID());
    }

}
