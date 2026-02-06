package com.jiankun.od;

import java.util.Scanner;

public class 天然蓄水池 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.nextLine();
        String[] s = next.split(" ");

        int length = s.length;
        long maxValue = 0;
        if (length <= 2) {
            System.out.println(maxValue);
        }

        long[] l = new long[length];
        for (int i = 0; i < length; i++) {
            l[i] = Long.parseLong(s[i]);
        }
        int left = 0;
        int right = length - 1;
        int ll = 0;
        int rr = length - 1;
        while (left < right) {
            long sum = 0;
            long minLength = Math.min(l[left], l[right]);
            for (int i = left + 1; i < right; i++) {
                if (l[i] < minLength) {
                    sum += minLength - l[i];
                }
            }
            if (sum >= maxValue) {
                maxValue = sum;
                ll = left;
                rr = right;
            }
            // 短指针向长指针靠拢
            if (l[left] < l[right]) {
                left++;
            } else {
                right--;
            }
        }
        if (maxValue == 0) {
            System.out.println(maxValue);
        } else {
            System.out.println(ll + " " + rr + ":" + maxValue);
        }
    }
}
