package com.icfcc.db.dal;

import java.util.HashMap;
import java.util.Map;

import cn.uncode.dal.criteria.DalCriteria;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.uncode.dal.core.BaseDAL;
import cn.uncode.dal.criteria.DalCriteria.Criteria;
import cn.uncode.dal.descriptor.QueryResult;
import cn.uncode.dal.utils.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class MybatisDALTest {

    @Autowired
    private BaseDAL baseDAL;

    @Test
    public void testSelectByCriteria() {
//        PageHelper.startPage(2, 2);
        DalCriteria dalCriteria = new DalCriteria();
        dalCriteria.setTable(User.class);
        Criteria critera = dalCriteria.createCriteria();
//        critera.andColumnGreaterThan(User.ID, 1);
//        critera.andColumnLessThanOrEqualTo(User.ID, 10000);
        QueryResult result = baseDAL.selectByCriteria(dalCriteria);
        System.out.println(result.getList().get(0).getClass());
        System.out.println(result.getList());
    }

    @Test
    public void testSelectByPrimaryKey1() {
        sample.User user = new sample.User();
        user.setID(1);
        SC sc = new SC(1, 1);
        QueryResult result = baseDAL.selectByPrimaryKey(sc);
        System.out.println(result.get());
    }
//    @Test
//    public void testSelectByPrimaryKey2(){
//    	QueryResult result =  baseDAL.selectByPrimaryKey("user", 1);
//        System.out.println(result.get());
//    }
//    @Test
//    public void testSelectByPrimaryKey3(){
//        QueryResult result =  baseDAL.selectByPrimaryKey(User.class, 1);
//        System.out.println(result.get());
//    }

    @Test
    public void testInsert1() {
        User user = new User();
        user.setId(13);
        user.setName("test001236501");
        SC sc = new SC(3,5);
        sc.setScore(130);
        Object result = baseDAL.insert(user);
        System.out.println(result);
    }

    @Test
    public void testInsert2() {
        Map<String, Object> content = new HashMap<>();
        content.put("username", "test001236501");
        content.put("id",14);
        Object result = baseDAL.insert("user", content);
        System.out.println(result);
    }


    @Test
    public void testDeleteByPrimaryKey1() {
        User user = new User();
        user.setId(13);
        SC sc = new SC(3,5);
        int result = baseDAL.deleteByPrimaryKey(sc);
        System.out.println(result);
    }

    @Test
    public void testDeleteByPrimaryKey2() {
        int result = baseDAL.deleteByPrimaryKey(User.class, 14);
        System.out.println(result);
    }

    @Test
    public void testDeleteByPrimaryKey3() {
        int result = baseDAL.deleteByPrimaryKey("user", 165);
        System.out.println(result);
    }

    @Test
    public void testDeleteByCriteria() {
        DalCriteria dalCriteria = new DalCriteria();
        dalCriteria.setTable(User.class);
        Criteria critera = dalCriteria.createCriteria();
        critera.andColumnEqualTo(User.ID, "13");
        int result = baseDAL.deleteByCriteria(dalCriteria);
        System.out.println(result);
    }

//    @Test
//    public void testUpdateByCriteria() {
//        User user = new User();
//        user.setEmail("test6@xiaocong.tv");
//        DalCriteria queryCriteria = new DalCriteria();
//        queryCriteria.setTable(User.class);
//        Criteria critera = queryCriteria.createCriteria();
//        critera.andColumnEqualTo(User.NAME, "双二一");
//        int result = baseDAL.updateByCriteria(user, queryCriteria);
//        System.out.println(result);
//    }

    @Test
    public void testUpdateByPrimaryKey() {
        User user = new User();
        user.setEmail("test@xiaocong.tv");
        user.setId(12);
        SC sc = new SC(1,1);
        sc.setScore(120);
        int result = baseDAL.updateByPrimaryKey(sc);
        System.out.println(result);
    }

    @Test
    public void testUpdateByPrimaryKey2() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("email", "test@xiaocong.tv");
        int result = baseDAL.updateByPrimaryKey("user", map);
        System.out.println(result);
    }

    @Test
    public void testMapToBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("usErnaMe", "123");
        map.put("pwd", "333333333");
        User user = JsonUtils.mapToObj(map, User.class);
    }

}
