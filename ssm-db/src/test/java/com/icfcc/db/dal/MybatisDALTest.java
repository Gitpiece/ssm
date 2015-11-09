package com.icfcc.db.dal;

import java.util.HashMap;
import java.util.Map;

import cn.uncode.dal.criteria.Criteria;
import cn.uncode.dal.criteria.DalCriteria;
import com.icfcc.db.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.uncode.dal.core.BaseDAL;
import cn.uncode.dal.descriptor.DalResult;
import cn.uncode.dal.utils.JsonUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class MybatisDALTest {

    @Autowired
    private BaseDAL baseDAL;

    @Test
    public void testSelectByCriteria() {
        PageHelper.startPage(2, 2);
        DalCriteria dalCriteria = new DalCriteria();
        dalCriteria.setTable(User.class);
        Criteria critera = dalCriteria.createCriteria();
//        critera.andColumnGreaterThan(User.ID, 1);
//        critera.andColumnLessThanOrEqualTo(User.ID, 10000);
        DalResult result = baseDAL.selectByCriteria(dalCriteria);
        System.out.println(result.getList().get(0).getClass());
        System.out.println(result.getList().size());
        System.out.println(result.getList());
    }

    @Test
    public void testSelectByPrimaryKey1() {
        sample.User user = new sample.User();
        user.setID(1);
        SC sc = new SC(1, 1);
        DalResult result = baseDAL.selectByPrimaryKey(user);
        System.out.println(result.get());
        System.out.println(result.getList());
    }
    @Test
    public void testSelectByPrimaryKey2(){
    	DalResult result =  baseDAL.selectByPrimaryKey("user", 1);
        System.out.println(result.get());
    }
    @Test
    public void testSelectByPrimaryKey3(){
        DalResult result =  baseDAL.selectByPrimaryKey(User.class, 1);
        System.out.println(result.get());
    }

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
        int result = baseDAL.deleteByPrimaryKey(user);
        System.out.println(result);
    }

    @Test
    public void testDeleteByPrimaryKey2() {
        int result = baseDAL.deleteByPrimaryKey(User.class, 14);
        System.out.println(result);
    }

    @Test
    public void testDeleteByPrimaryKey3() {
        int result = baseDAL.deleteByPrimaryKey("user", 14);
        System.out.println(result);
    }

    @Test
    public void testDeleteByCriteria() {
        DalCriteria dalCriteria = new DalCriteria();
        dalCriteria.setTable(User.class);
        Criteria critera = dalCriteria.createCriteria();
        critera.andColumnEqualTo(User.ID, "13");
        critera.andColumnEqualTo(User.NAME, "test001236501");
        int result = baseDAL.deleteByCriteria(dalCriteria);
        System.out.println(result);
    }

    @Test
    public void testUpdateByCriteria() {
        User user = new User();
        user.setEmail("test6@xiaocong.tv");
        DalCriteria Criteria = new DalCriteria();
        Criteria.setTable(User.class);
        Criteria critera = Criteria.createCriteria();
        critera.andColumnEqualTo(User.NAME, "双十一a");
        int result = baseDAL.updateByCriteria(user, Criteria);
        System.out.println(result);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        User user = new User();
        user.setEmail("test@xiaocong.tv12");
        user.setId(12);
        SC sc = new SC(1,1);
        sc.setScore(12);
        int result = baseDAL.updateByPrimaryKey(user);
        System.out.println(result);
        result = baseDAL.updateByPrimaryKey(sc);
        System.out.println(result);
    }

    @Test
    public void testUpdateByPrimaryKey2() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("email", "test@xiaocong.tv2");
        int result = baseDAL.updateByPrimaryKey("user", map);
        System.out.println(result);
    }

    @Test
    public void testMapToBean() {
        Map<String, Object> map = new HashMap<>();
        map.put("NAME", "123");
        map.put("ID", 1);
        map.put("AGE", "2S");
        sample.User user = JsonUtils.mapToObj(map, sample.User.class);
        System.out.println(user);
    }

}
