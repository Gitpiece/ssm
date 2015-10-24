package sample;


import com.icfcc.db.anon.MyBatisDao;
import com.icfcc.db.base.BaseMapper;
import org.apache.ibatis.session.SqlSession;

@MyBatisDao
public interface UserMapper2<T,PK> extends BaseMapper<T,PK>{
//    SqlSession getSqlSession();
}