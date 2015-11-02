package sample;

import com.icfcc.TestBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;


/**
 * Created by root on 15-7-9.
 */
public class UserServiceTest extends TestBase {

    public static final Log logger = LogFactory.getFactory().getInstance(UserServiceTest.class);
    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private UserService userServiceImpl;
    @Autowired(required = false)
    private UserMapper userMapper;


    @Before
    public void before(){
//        printwac(wac);
//        userMapper = wac.getBean(UserMapper.class);
//        System.out.println(userMapper);
//        Assert.assertNotNull(userService);
    }

    @Test
    public void printwac(){
        String[] dn = wac.getBeanDefinitionNames();
        for (int i = 0; i < dn.length; i++) {
            System.out.println(dn[i]);
        }
    }

    @Test
    public void testAddUser(){

        sample.User user = userServiceImpl.select(1);
        System.out.println(user.getNAME());
//
//        user = new User();
//        user.setNAME("test");
//        user.setAGE(23);
//        user.setID(23);
//        userService.addUser(user);
//        System.out.println(user.getID());
    }

    @Test
    public void test(){

    }
}
