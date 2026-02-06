package com.jiankun.od;

import java.util.Arrays;
import java.util.Scanner;

public class 小明减肥 {
    static int count = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] s1 = in.nextLine().split(" ");
        int n = Integer.parseInt(s1[0]); // n个运动
        int t = Integer.parseInt(s1[1]); // 卡路里和为t
        int k = Integer.parseInt(s1[2]); // 选取k个卡路里和为t
        Integer[] array = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        boolean[] flag = new boolean[n];
        getNum(array, flag, t, k, 0);
        System.out.println(count);
    }

    public static void getNum(Integer[] array, boolean[] flag, int t, int k, int index) {
        if (k == 1) {
            for (int i = index; i < array.length; i++) {
                if (!flag[i] && array[i] == t) {
                    count++;
                }
            }
            return;
        }
        for (int i = index; i < array.length; i++) {
            if (!flag[i] && array[i] < t) {
                flag[i] = true;
                getNum(array, flag, t - array[i], k - 1, i + 1);
                flag[i] = false;
            }
        }
    }
}
