package com.icfcc.db.dal.descriptor;

import org.junit.Test;
import org.springframework.util.Assert;
import sample.User;

import java.util.Collection;

/**
 * for TableInfoResolver test
 * Created by WangHuanyu on 2015/11/4.
 */
public class TableInfoResolverTest {

    @Test
    public void resolverTable(){
        Assert.notNull(TableInfoResolver.resolverTable(User.class));
        System.out.println("table : "+ TableInfoResolver.resolverTable(User.class));
    }

    @Test
    public void resolverTableColumn(){
        Collection<Column> columns = TableInfoResolver.resolverTableColumns(User.class);
        for(Column column:columns){
            System.out.println(column.toString());
        }
    }
}
