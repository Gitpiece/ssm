package com.icfcc.db.sys;

import com.icfcc.db.MybatisSqlSessionHelper;
import com.icfcc.db.user.SmUserAuth;
import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by admin on 2015/11/3.
 */
public class SmMenuMapperTest extends MybatisSqlSessionHelper {
    String namespace = "com.icfcc.db.sys.SmMenuMapper";
    @Test
    public void selectTest(){
        SqlSession sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
        SmMenuMapper smMenuMapper = sqlSession.getMapper(SmMenuMapper.class);
        SmMenu smMenu = smMenuMapper.selectByPrimaryKey(1);
        Assert.assertNotNull(smMenu);
        System.out.println(smMenu.getsName());
    }
}
