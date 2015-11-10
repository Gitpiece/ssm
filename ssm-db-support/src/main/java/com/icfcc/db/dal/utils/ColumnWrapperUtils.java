package com.icfcc.db.dal.utils;

public class ColumnWrapperUtils {

    private static final String KEY_WORDS = "index,key,value,table,option,fields,version,user,name,status,desc";

    public static String wrap(String column) {
        if (KEY_WORDS.contains(column)) {
            return "`" + column + "`";
        } else {
            return column;
        }
    }

}

