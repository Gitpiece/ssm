package com.icfcc.common.utils;

import com.icfcc.util.DateUtils;
import org.junit.Test;

import java.util.Date;

/**
 * Created by admin on 2015/7/22.
 */
public class DateUtilsTest {

    @Test
    public void test(){
        System.out.println(DateUtils.formatDate(DateUtils.parseDate("2010/3/6")));
		System.out.println(DateUtils.getDate("yyyy年MM月dd日 E"));
		long time = new Date().getTime()-DateUtils.parseDate("2012-11-19").getTime();
		System.out.println(time/(24*60*60*1000));
    }
}
