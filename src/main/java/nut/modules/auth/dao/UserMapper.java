package nut.modules.auth.dao;

import nut.modules.auth.model.User;
import nut.persistence.annotation.MyBatisDao;

@MyBatisDao
public interface UserMapper {
    int deleteByPrimaryKey(Integer ID);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer ID);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}