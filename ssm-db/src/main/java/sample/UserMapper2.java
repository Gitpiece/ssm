package sample;


import com.icfcc.db.anon.MyBatisDao;
import com.icfcc.db.BaseMapper;

import java.util.List;

@MyBatisDao
public interface UserMapper2<T,PK> extends BaseMapper<T,PK>{
//    SqlSession getSqlSession();

    List<T> select(User user);
}