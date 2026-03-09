package com.jiankun.test.双机位C;

import java.util.Arrays;
import java.util.Scanner;

// 天然蓄水池
public class Main001 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] height = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int left = 0;
        int right = height.length - 1;
        int result = 0;
        int ll = 0;
        int rr = 0;
        while (left < right) {
            int minLength = Math.min(height[left], height[right]);
            int temp = 0;
            for (int i = left + 1; i < right; i++) {
                if (height[i] < minLength) {
                    temp += minLength - height[i];
                }
            }
            if (temp >= result) {
                result = temp;
                ll = left;
                rr = right;
            }
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        if (result != 0) {
            System.out.println(ll + " " + rr + ":" + result);
        } else {
            System.out.println(0);
        }
    }
}
