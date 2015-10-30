package sample;

import com.icfcc.db.orderhelper.OrderByHelper;
import com.icfcc.db.pagehelper.PageHelper;
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

import java.util.List;


/**
 * Created by root on 15-7-9.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration(value = "src/main/webapp")
//@ContextHierarchy({
//        @ContextConfiguration(name = "parent", locations = {"classpath:spring-context*.xml","classpath:spring-mvc*.xml", "classpath:spring-sample-mvc.xml"}),
////        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc*.xml")
//})
public class UserService2Test {

    public static final Log logger = LogFactory.getFactory().getInstance(UserService2Test.class);
    @Autowired
    private WebApplicationContext wac;
    @Autowired(required = false)
    private UserServiceImpl2 userServiceImpl2;
    @Autowired(required = false)
    private SqlSession sqlSession;

    @Before
    public void before(){
//        userMapper = wac.getBean(UserMapper.class);
//        System.out.println(userMapper);
//        Assert.assertNotNull(userServiceImpl2);
    }

//    @Test
    public void printwac(){
        String[] dn = wac.getBeanNamesForType(SqlSession.class);
        for (int i = 0; i < dn.length; i++) {
            System.out.println(dn[i]);
        }
    }

//    @Test
    public void testSelect(){

        User user = userServiceImpl2.select(1);
        System.out.println(user.getNAME());
//
//        user = new User();
//        user.setNAME("test");
//        user.setAGE(23);
//        user.setID(23);
//        userServiceImpl2.addUser(user);
//        System.out.println(user.getID());
    }

    /**
     * 测试分页和排序
     */
//    @Test
    public void testSelectPageAndOrder(){
        OrderByHelper.orderBy("id ");
        PageHelper.startPage(1, 5);
        List<User> list = userServiceImpl2.select(new User());
        System.out.println(list.getClass().toString());
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("id %d, name %s, age %d\n",list.get(i).getID(),list.get(i).getNAME(),list.get(i).getAGE());
        }
    }

}
