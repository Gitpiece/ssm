package nut.auth.service;

import nut.auth.dao.UserMapper;
import nut.auth.model.User;

/**
 * Created by wanghuanyu on 2015/7/9.
 */
public class UserServiceImpl implements UserService{
    private UserMapper userMapper;
    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    public User getUserById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User select(Integer userid){
       return this.userMapper.selectByPrimaryKey(userid);
    }
}
