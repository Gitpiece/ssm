package com.icfcc.db.dal.mongo;

import java.util.HashMap;
import java.util.Map;

import com.icfcc.db.dal.criteria.Criteria;
import com.icfcc.db.dal.criteria.DalCriteria;
import com.icfcc.db.dal.descriptor.DalResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-mongo.xml")
public class MongoTest {
    
    @Autowired
    private MongoDAL mongoDAL;
    
    @Test
    public void testSelectByCriteria(){
        DalCriteria queryCriteria = new DalCriteria();
        queryCriteria.setTable("news");
        DalResult result =  mongoDAL.selectByCriteria(queryCriteria);
        System.out.println(result.getList());
    }
    
    @Test
    public void testSelectByPrimaryKey(){
        Map<String, Object> map = new HashMap<String, Object>();
    	map.put("_id", "54af9416332dc3759af0956d");
    	DalResult result =  mongoDAL.selectByPrimaryKey("news", map);
        System.out.println(result.get());
    }
    
    
    @Test
    public void testInsert(){
    	Map<String, Object> content = new HashMap<String, Object>();
    	content.put("title", "test001236501685");
    	content.put("status", 0);
    	content.put("name", "123652");
    	Object result = mongoDAL.insert("news", content);
        System.out.println(result);
    }
    
    
    
    @Test
    public void testDeleteByPrimaryKey(){
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("_id", "54af9416332dc3759af0956d");
        int result = mongoDAL.deleteByPrimaryKey("news", map);
        System.out.println(result);
    }
    
    
    @Test
    public void testDeleteByCriteria(){
        DalCriteria queryCriteria = new DalCriteria();
        queryCriteria.setTable("news");
        Criteria critera = queryCriteria.createCriteria();
        critera.andColumnEqualTo("status", 1);
        int result = mongoDAL.deleteByCriteria(queryCriteria);
        System.out.println(result);
    }
    
    @Test
    public void testUpdateByCriteria(){
    	Map<String, Object> content = new HashMap<String, Object>();
    	content.put("status", 1);
        DalCriteria queryCriteria = new DalCriteria();
        queryCriteria.setTable("news");
        Criteria critera = queryCriteria.createCriteria();
        critera.andColumnEqualTo("title", "test001236501");
        int result = mongoDAL.updateByCriteria(content, queryCriteria);
        System.out.println(result);
    }
    
    @Test
    public void testUpdateByPrimaryKey(){
    	Map<String, Object> content = new HashMap<String, Object>();
    	content.put("status", 1);
    	content.put("_id", "54af941a332d1bd65e724c7f");
        int result = mongoDAL.updateByPrimaryKey("news", content);
        System.out.println(result);
    }
    


}
