package com.icfcc.db;

import com.icfcc.db.pagehelper.Page;
import com.icfcc.db.plugin.ExamplePagePlugin;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import sample.User;
import sample.UserMapper;
import sample.UserMapper2;

import java.io.InputStream;
import java.util.List;

/**
 * Created by admin on 2015/11/3.
 */
public class MybatisSqlSessionHelper {
    protected SqlSessionFactory sqlSessionFactory = null;

    @Test
    public void test() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //configuration
        Configuration configuration = sqlSessionFactory.getConfiguration();
        configuration.addInterceptor(new ExamplePagePlugin());

        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper2 userMapper = sqlSession.getMapper(UserMapper2.class);
        List<User> list = userMapper.select(new User());

        System.out.println(list.getClass().toString());
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("id %d, name %s, age %d\n",list.get(i).getID(),list.get(i).getNAME(),list.get(i).getAGE());
        }
        if(list instanceof Page){
            Page page = (Page)list;
            System.out.printf("共 %d 条数据,第 %d 页,共 %d 页,每页%d条数据,当前页%d条数据。\n",
                    page.getTotal(),
                    page.getPageNum(),
                    page.getPages(),
                    page.getPageSize(),
                    page.getEndRow()-page.getStartRow());
            System.out.println(page.getStartRow());
            System.out.println(page.getEndRow());
        }
    }
}
