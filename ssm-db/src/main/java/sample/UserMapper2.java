package sample;


import com.icfcc.db.anon.MyBatisDao;
import com.icfcc.db.base.BaseMapper;

@MyBatisDao
public interface UserMapper2 extends BaseMapper<User,Integer>{

}