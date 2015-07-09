package nut.util;

import org.testng.annotations.Test;

/**
 * Created by root on 15-6-13.
 */
public class SortUtilTest {

    @Test
    public void testsortpopup(){
        Integer[] array = new Integer[]{3,44,2,56,7,8,33};
        array = SortUtil.sortPopup(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }
}
