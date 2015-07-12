//package nut.auth;
//
//
//import nut.auth.model.User;
//import nut.auth.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.testng.annotations.Test;
//
////配置了@ContextConfiguration注解并使用该注解的locations属性指明spring和配置文件之后，
//@ContextConfiguration(locations = {"classpath:spring-mybatis.xml" })
//public class MyBatisTestBySpringTestFramework {
//
//    //注入userService
//    @Autowired
//    private UserService userService;
//
//    @Test
//    public void testAddUser(){
//        User user = new User();
//        user.setNAME("xdp_gacl_白虎神皇");
//        user.setAGE(12);
//        userService.addUser(user);
//    }
//
//    @Test
//    public void testGetUserById(){
//        User user = userService.getUserById(2);
//        System.out.println(user.getNAME());
//    }
//
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }
//}