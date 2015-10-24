package sample;

/**
 * Created by admin on 2015/8/16.
 */

import java.util.List;

/**
 * Created by wanghuanyu on 2015/7/9.
 */

public interface UserService {

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);

    User select(Integer userid);

    public List<User> select(User user);
}