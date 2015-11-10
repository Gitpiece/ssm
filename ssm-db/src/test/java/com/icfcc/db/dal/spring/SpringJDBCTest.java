package com.icfcc.db.dal.spring;

import java.util.HashMap;
import java.util.Map;

import com.icfcc.db.dal.core.BaseDAL;
import com.icfcc.db.dal.criteria.Criteria;
import com.icfcc.db.dal.criteria.DalCriteria;
import com.icfcc.db.dal.criteria.Model;

import com.icfcc.db.dal.descriptor.DalResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-spring.xml")
public class SpringJDBCTest {

    @Autowired
    private BaseDAL baseDAL;

    @Test
    public void testSelectByCriteria() {
        DalCriteria queryCriteria = new DalCriteria();
        queryCriteria.setTable(User.class);
        Criteria critera = queryCriteria.createCriteria();
        critera.andColumnGreaterThan(User.ID, 1);
        critera.andColumnLessThanOrEqualTo(User.ID, 10000);
        DalResult result = baseDAL.selectByCriteria(queryCriteria);
        System.out.println(result.getList());
    }

    @Test
    public void testSelectByPrimaryKey1() {
        User user = new User();
        user.setId(1);
        DalResult result = baseDAL.selectByPrimaryKey(user);
        System.out.println(result.get());
    }

    @Test
    public void testSelectByPrimaryKey2() {
        DalResult result = baseDAL.selectByPrimaryKey("user", 1);
        System.out.println(result.get());
    }

    @Test
    public void testSelectByPrimaryKey3() {
        DalResult result = baseDAL.selectByPrimaryKey(User.class, 1);
        System.out.println(result.get());
    }

    @Test
    public void testSelectByPrimaryKey4() {
        DalResult result = baseDAL.selectByPrimaryKey("users", "test001236501");
        System.out.println(result.get());
    }

    @Test
    public void testInsert1() {
        User user = new User();
        user.setUserName("test001236501");
        Object result = baseDAL.insert(user);
        System.out.println(result);
    }

    @Test
    public void testInsert2() {
        Model model = new Model(User.class);
        Map<String, Object> content = new HashMap<>();
        content.put(User.USER_NAME, "test001236501");
        model.addContent(content);
        Object result = baseDAL.insert(model);
        System.out.println(result);
    }

    @Test
    public void testInsert3() {
        Map<String, Object> content = new HashMap<>();
        content.put("user_id", "test001236501");
        content.put("user_name", "test001236501");
        Object result = baseDAL.insert("users", content);
        System.out.println(result);
    }


    @Test
    public void testDeleteByPrimaryKey1() {
        User user = new User();
        user.setId(165);
        int result = baseDAL.deleteByPrimaryKey(user);
        System.out.println(result);
    }

    @Test
    public void testDeleteByPrimaryKey2() {
        int result = baseDAL.deleteByPrimaryKey(User.class, 1);
        System.out.println(result);
    }

    @Test
    public void testDeleteByPrimaryKey3() {
        int result = baseDAL.deleteByPrimaryKey("user", 165);
        System.out.println(result);
    }

    @Test
    public void testDeleteByPrimaryKey4() {
        int result = baseDAL.deleteByPrimaryKey("users", "test001236501");
        System.out.println(result);
    }

    @Test
    public void testDeleteByCriteria() {
        DalCriteria queryCriteria = new DalCriteria();
        queryCriteria.setTable(User.class);
        Criteria critera = queryCriteria.createCriteria();
        critera.andColumnEqualTo(User.USER_NAME, "test001236501");
        int result = baseDAL.deleteByCriteria(queryCriteria);
        System.out.println(result);
    }

    @Test
    public void testUpdateByCriteria() {
        User user = new User();
        user.setEmail("test6@xiaocong.tv");
        DalCriteria queryCriteria = new DalCriteria();
        queryCriteria.setTable(User.class);
        Criteria critera = queryCriteria.createCriteria();
        critera.andColumnEqualTo(User.USER_NAME, "test001236501");
        int result = baseDAL.updateByCriteria(user, queryCriteria);
        System.out.println(result);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        User user = new User();
        user.setEmail("test@xiaocong.tv");
        user.setId(163);
        int result = baseDAL.updateByPrimaryKey(user);
        System.out.println(result);
    }

}
