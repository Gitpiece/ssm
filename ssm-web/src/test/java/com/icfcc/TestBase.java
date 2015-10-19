package com.icfcc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")
@ContextHierarchy({
        @ContextConfiguration(name = "parent", locations = {"classpath:spring-context-cache.xml", "classpath:spring-context-mybatis.xml", "classpath:spring-context-shiro.xml", "classpath:spring-mvc.xml", "classpath:spring-sample-mvc.xml"}),
//        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc*.xml")
})

public class TestBase {
    @Test
    public void test() {
    }
}