package sample;

/**
 * Created by admin on 2015/8/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanghuanyu on 2015/7/9.
 */
@Service
public class UserServiceImpl2 implements UserService {

    @Autowired
    private UserMapper2<User,Integer> userMapper2;

    @Override
    public void addUser(User user) {
        userMapper2.insert(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper2.selectByPrimaryKey(userId);
    }

    public void setUserMapper(UserMapper2 userMapper) {
        this.userMapper2 = userMapper;
    }

    @Override
    public User select(Integer userid){
        return this.userMapper2.selectByPrimaryKey(userid);
    }
}
