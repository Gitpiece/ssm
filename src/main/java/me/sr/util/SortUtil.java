package me.sr.util;

/**
 * Created by root on 15-6-13.
 */
public class SortUtil {

    /**
     * popup sorting (assent)
     * @param integers
     * @return
     */
    public static Integer[] sortPopup(Integer[] integers){
        Integer [] sortedIntegers = new Integer[integers.length];
        System.arraycopy(integers,0,sortedIntegers,0,integers.length);

        int arrayLength = sortedIntegers.length;
        for (int i = 0; i < arrayLength-1; i++) {
            int a = sortedIntegers[i];
            for (int j = i+1; j < arrayLength; j++) {
                int b = sortedIntegers[j];
                if(a > b){
                    sortedIntegers[i] = b;
                    sortedIntegers[j] = a;
                    a = b;
                }
            }
        }

        return sortedIntegers;
    }
}
