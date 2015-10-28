package sample.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 模拟http请求，测试完整的spring mvc功能。
 * 注：目前只测试了junit4以上版本。
 */
//XML风格
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = {"classpath:spring-context.xml","classpath:spring-context-cache.xml","classpath:spring-context-mybatis.xml","classpath:spring-context-shiro.xml","classpath:spring-mvc.xml", "classpath:spring-sample-mvc.xml"}),
//        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc*.xml")
})

//注解风格
//@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration(value = "src/main/webapp")
//@ContextHierarchy({
//        @ContextConfiguration(name = "parent", classes = AppConfig.class),
//        @ContextConfiguration(name = "child", classes = MvcConfig.class)
//})
public class HelloWorldControllerTest {//extends AbstractTestNGSpringContextTests {

    public static final Log logger = LogFactory.getFactory().getInstance(HelloWorldControllerTest.class);
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void printBeanNames(){
        logger.info("printBeanNames...");
        String[] names = wac.getBeanDefinitionNames();
        for (int i = 0; i < names.length; i++) {
            System.out.printf("beannane: %s, classname:%s \n",names[i],wac.getBean(names[i]).getClass().toString());
        }
    }

    @Test
    public void hellowordtest() throws Exception {
        logger.info("hellowordtest...");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/helloword"))
                .andExpect(MockMvcResultMatchers.view().name("helloword"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(result);
        System.out.println(result.getResponse());
    }

    @Test
    public void viewTest()throws Exception {
        logger.info("viewTest...");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/helloword/{id}", 1)) //执行请求
                .andExpect(MockMvcResultMatchers.model().attributeExists("user")) //验证存储模型数据
                .andExpect(MockMvcResultMatchers.view().name("helloword")) //验证viewName
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/sample/helloword.jsp"))//验证视图渲染时forward到的jsp
                .andExpect(MockMvcResultMatchers.status().isOk())//验证状态码
                .andDo(MockMvcResultHandlers.print())//输出MvcResult到控制台
                .andReturn();
        System.out.println(result);
        System.out.println(result.getResponse());
    }

//    @Test
    public void notFoundtest() {
        try {
            logger.info("测试普通控制器，但是URL错误，即404");
            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user2/{id}", 1)) //执行请求
                    .andExpect(MockMvcResultMatchers.status().isNotFound()) //验证控制器不存在
                    .andDo(MockMvcResultHandlers.print())//输出MvcResult到控制台
                    .andReturn();
        }catch (Exception e){
            Assert.assertNotNull(e);
        }
    }


    public void testJSON(){
        String requestBody = "{\"id\":1, \"name\":\"zhang\"}";
//        mockMvc.perform(MockHttpServletRequestBuilder.post("/user")
//                .contentType(MediaType.APPLICATION_JSON).content(requestBody)
//                .accept(MediaType.APPLICATION_JSON)) //执行请求
//                .andExpect(JsonPathResultMatchers.content().contentType(MediaType.APPLICATION_JSON)) //验证响应contentType
//                .andExpect(JsonPathResultMatchers.jsonPath("$.id").value(1)); //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/
//
//        String errorBody = "{id:1, name:zhang}";
//        MvcResult result = mockMvc.perform(post("/user")
//                .contentType(MediaType.APPLICATION_JSON).content(errorBody)
//                .accept(MediaType.APPLICATION_JSON)) //执行请求
//                .andExpect(status().isBadRequest()) //400错误请求
//                .andReturn();
//
//        Assert.assertTrue(HttpMessageNotReadableException.class.isAssignableFrom(result.getResolvedException().getClass()));//错误的请求内容体
    }

    @Test
    public void staticResourceTest() throws Exception{
        //静态资源
        mockMvc.perform(MockMvcRequestBuilders.get("/static/jquery/jquery-1.11.2.min.js")) //执行请求
                .andExpect(MockMvcResultMatchers.status().isOk()) //验证状态码200
                //.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("var")))//验证渲染后的视图内容包含var
                .andDo(MockMvcResultHandlers.print())//输出MvcResult到控制台
                .andReturn();
        mockMvc.perform(MockMvcRequestBuilders.get("/static/app1.js")) //执行请求
                .andExpect(MockMvcResultMatchers.status().isNotFound())  //验证状态码404
                .andDo(MockMvcResultHandlers.print())//输出MvcResult到控制台
                .andReturn();
    }

    @Test
    public void internalServerErrorTest()throws Exception{
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/helloword/exception")) //执行请求
                .andExpect(MockMvcResultMatchers.status().isInternalServerError()) //验证服务器内部错误500
                .andDo(MockMvcResultHandlers.print())//输出MvcResult到控制台
                .andReturn();

        Assert.assertTrue(IllegalArgumentException.class.isAssignableFrom(result.getResolvedException().getClass()));
    }


}