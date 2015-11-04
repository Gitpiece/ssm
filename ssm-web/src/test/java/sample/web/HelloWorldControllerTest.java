package sample.web;

import com.icfcc.TestBase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class HelloWorldControllerTest extends TestBase {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }



    @Test
    public void hellowordtestHTML() throws Exception {
        logger.info("hellowordtest...");
        ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/helloword"));
        ra.andDo(MockMvcResultHandlers.print());
        MvcResult result = ra.andReturn();
//                mockMvc.perform(MockMvcRequestBuilders.get("/helloword"))
//                .andExpect(MockMvcResultMatchers.view().name("sample/helloword"))
////                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }
    @Test
    public void hellowordtestJSON() throws Exception {
        logger.info("hellowordtest...");
        ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/helloword.json?pagesize=5&pagenum=2"));
        ra.andDo(MockMvcResultHandlers.print());
        MvcResult result = ra.andReturn();
//                mockMvc.perform(MockMvcRequestBuilders.get("/helloword"))
//                .andExpect(MockMvcResultMatchers.view().name("sample/helloword"))
////                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }
    @Test
    public void hellowordtestXML() throws Exception {
        logger.info("hellowordtest...");
        ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/helloword.xml"));
        ra.andDo(MockMvcResultHandlers.print());
        MvcResult result = ra.andReturn();
//                mockMvc.perform(MockMvcRequestBuilders.get("/helloword"))
//                .andExpect(MockMvcResultMatchers.view().name("sample/helloword"))
////                .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
//                .andDo(MockMvcResultHandlers.print())
//                .andReturn();
    }

    @Test
    public void viewTest()throws Exception {
        logger.info("viewTest...");
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/helloword/{id}", 1)) //执行请求
                .andExpect(MockMvcResultMatchers.model().attributeExists("user")) //验证存储模型数据
                .andExpect(MockMvcResultMatchers.view().name("sample/helloword")) //验证viewName
                .andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/sample/helloword.jsp"))//验证视图渲染时forward到的jsp
                .andExpect(MockMvcResultMatchers.status().isOk())//验证状态码
                .andDo(MockMvcResultHandlers.print())//输出MvcResult到控制台
                .andReturn();
        System.out.println(result);
        System.out.println(result.getResponse());
    }

//    @Test
//    public void notFoundtest() {
//        try {
//            logger.info("测试普通控制器，但是URL错误，即404");
//            MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/hellowordXX")) //执行请求
//                    .andExpect(MockMvcResultMatchers.status().isNotFound()) //验证控制器不存在
//                    .andDo(MockMvcResultHandlers.print())//输出MvcResult到控制台
//                    .andReturn();
//        }catch (Exception e){
//            Assert.assertNotNull(e);
//        }
//    }


    @Test
    public void staticResourceTest() throws Exception{
        //静态资源
        mockMvc.perform(MockMvcRequestBuilders.get("/static/jquery/jquery-1.11.2.min.js"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("var")))//验证渲染后的视图内容包含var
                .andDo(MockMvcResultHandlers.print())
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