package nut;

import java.io.*;

public class Solution {

    /**
     * popup sorting (assent)
     * @param integers
     * @return
     */
    public static int[] sortPopup(int[] integers){
        int [] sortedIntegers = new int[integers.length];
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

    public static void main( String[] args ) throws NumberFormatException,IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        int K = Integer.parseInt(in.readLine());
        int[] list = new int[N];

        for(int i = 0; i < N; i ++)
            list[i] = Integer.parseInt(in.readLine());

        int unfairness = Integer.MAX_VALUE;

      /*
       * Write your code here, to process numPackets N, numKids K, and the packets of candies
       * Compute the ideal value for unfairness over here
      */
        int tempunfairness = 0;

        list = sortPopup(list);
        int length = list.length;
        System.out.printf(" ");
        for (int i = 0; i < length - K ; i++) {
            tempunfairness = list[K+i-1]-list[i];
            if(unfairness > tempunfairness)unfairness = tempunfairness;
        }

        System.out.println(unfairness);

    }
}
