package com.jiankun.od.双机位C.No16_数组连续和;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

// 暴力
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = Arrays.stream(Arrays.copyOfRange(nums, i, j + 1)).sum();
                if (sum >= s) {
                    count += n - j;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
