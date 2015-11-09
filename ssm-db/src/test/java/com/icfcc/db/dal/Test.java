package com.icfcc.db.dal;

/**
 * Created by admin on 2015/11/9.
 */
public class Test {
    @org.junit.Test
    public void test(){
        User user = new User(2);
        System.out.println(user.getT().getClass());
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
