package com.icfcc.db.dal;

/**
 * Created by admin on 2015/11/9.
 */
public class Test {
    @org.junit.Test
    public void test(){
        User user = new User(2);
        System.out.println(user.getT().getClass());

        Integer i1 = 127;
        Integer i2 = 127;
        Integer i3 = 128;
        Integer i4 = 128;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
        System.out.println(Integer.toBinaryString(i1));
        System.out.println(Integer.toBinaryString(i3));
        System.out.println(0L == 0);
        System.out.println(((Long)0L).equals(0));
    }

    class User<T>{
        private T t ;
        public User(T t){
            this.t = t;
        }

        public T getT() {
            return t;
        }
    }
}
