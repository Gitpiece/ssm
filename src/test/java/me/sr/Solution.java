package me.sr;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int  size = in.nextInt();
        int[][] matrix = new int[size][size];
        for (int  x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                matrix[x][y] = in.nextInt();
            }
        }
        int leftcross=0,rightcross=0;
        for (int  x = 0; x < size; x++) {
            leftcross += matrix[x][x];
            rightcross += matrix[x][size-x-1];
        }
        System.out.println(Math.abs(leftcross-rightcross));
    }
}
