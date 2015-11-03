package com.icfcc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = {"classpath:spring-context.xml",
                "classpath:spring-context-cache.xml",
                "classpath:spring-context-service.xml",
                "classpath:spring-context-mybatis.xml",
                "classpath:spring-context-shiro.xml",
                "classpath:spring-mvc.xml",
                "classpath:spring-context-sample-mvc.xml"}),
//        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc*.xml")
})

public abstract class TestBase {
    public final Log logger = LogFactory.getFactory().getInstance(this.getClass());

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
}
