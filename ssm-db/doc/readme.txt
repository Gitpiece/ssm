三、字段(Field)  
字段是数据库中的用途最广泛的，它的类型非常多，所以必须加类型前缀来标示它的类型。  
字段名称 = F + 字段类型前缀 + 字段内容标识(首字大写) 。 
为了编程的方便性，可在前面加上字段类型的前缀，一般取用类型的三个字母，但是不需要下化线，
而且这三个字母必须小写；如姓名字段为字符型的话就应该为chrName；尝用字段类型的缩写可参考下面的形式：  
缩写 - 类型 
chr - char 
nvr - nvarchar
vcr - varchar
num - number
flt - float
dtm - date
tms - timestamp
lng - long 
int - integer
clb - clob
blb - blob
dcm - Decimal
bln - boolean

mysql备份还原方法
备份：
mysqldump -u root -p ssm > ssm.sql
还原：
use db;
source db.sql;