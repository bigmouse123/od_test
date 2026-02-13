package com.jiankun.od.双机位C.No4_小明减肥;

import java.util.Scanner;

/**
 * 动态规划(错误思路)
 * 动态规划求最大值
 * 该题目要求求方案数
 *
 * @since 2026/2/7
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 总共n个运动(相当于物品)
        int t = scanner.nextInt(); // 总共t卡路里(最终多少价值)
        int k = scanner.nextInt(); // 选择k个运动(相当于背包容量)
        int[] arr = new int[n]; // 每个运动消耗的卡路里(相当于每个物品的价值)
        int count = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        int[][] dp = new int[n][k + 1];
        for (int j = 1; j < k + 1; j++) {
            dp[0][j] = arr[0];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k + 1; j++) {
                if (j < 1) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] + arr[i]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k + 1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            if (dp[i][k] == t) {
                count++;
            }
        }
        System.out.println(count);
    }
}
