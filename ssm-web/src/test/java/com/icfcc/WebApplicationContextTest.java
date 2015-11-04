package com.icfcc;

import com.icfcc.db.sys.SmMenuMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * Created by admin on 2015/11/3.
 */
public class WebApplicationContextTest extends TestBase{
    @Test
    public void printBeanNames(){
        logger.info("printBeanNames...");
        String[] names = wac.getBeanDefinitionNames();
        for (int i = 0; i < names.length; i++) {
            System.out.printf("beannane: %s, Object : %s, classname:%s \n",names[i],wac.getBean(names[i]),wac.getBean(names[i]).getClass().toString());
        }
        System.out.println("total: " + names.length);
        SqlSessionFactory sqlSessionFactory = wac.getBean("sqlSessionFactory", SqlSessionFactory.class);
        System.out.println(sqlSessionFactory.openSession().getMapper(SmMenuMapper.class));
        System.out.println(sqlSessionFactory.openSession().getMapper(SmMenuMapper.class));
        System.out.println(sqlSessionFactory.openSession().getMapper(SmMenuMapper.class));
    }
}
