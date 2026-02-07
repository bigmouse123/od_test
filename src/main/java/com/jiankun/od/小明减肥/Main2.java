package com.jiankun.od.小明减肥;

import java.util.Scanner;

/**
 * 暴力回溯
 *
 * @since 2026/2/7
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 总共n个运动
        int t = scanner.nextInt(); // 总共t卡路里
        int k = scanner.nextInt(); // 选择k个运动
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }
        System.out.println(dfs(arr, t, k, 0, 0, 0));
    }

    /**
     * @param arr        运动数组
     * @param t          总共需要的卡路里
     * @param k          总共需要的运动个数
     * @param currentNum 当前已选运动个数
     * @param currentSum 当前已选运动卡路里
     * @param index      当前运动下标
     */
    public static int dfs(int[] arr, int t, int k, int currentNum, int currentSum, int index) {
        if (currentNum == k) {
            return currentSum == t ? 1 : 0;
        }
        if (index >= arr.length) {
            return 0;
        }
        if (currentNum < k && currentSum >= t) {
            return 0;
        }
        // 选择当前运动
        int count = dfs(arr, t, k, currentNum + 1, currentSum + arr[index], index + 1);
        // 不选择当前运动
        count += dfs(arr, t, k, currentNum, currentSum, index + 1);
        return count;
    }
}
