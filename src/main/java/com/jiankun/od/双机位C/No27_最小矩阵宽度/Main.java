package com.jiankun.od.双机位C.No27_最小矩阵宽度;

import java.util.*;

public class Main {
    // 超时
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // 1. 读取矩阵
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // 2. 读取目标数组，并用 Map 统计目标词频
        int K = scanner.nextInt();
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < K; i++) {
            int val = scanner.nextInt();
            targetMap.put(val, targetMap.getOrDefault(val, 0) + 1);
        }

        // 3. 初始化滑动窗口相关变量
        Map<Integer, Integer> windowMap = new HashMap<>();
        int l = 0;
        int r = 0;
        int ans = Integer.MAX_VALUE;

        // valid 记录窗口内满足数量要求的数字种类数
        int valid = 0;
        // required 记录目标数组中一共有多少种不同的数字
        int required = targetMap.size();

        while (r < M) {
            for (int i = 0; i < N; i++) {
                int num = matrix[i][r];
                if (targetMap.containsKey(num)) {
                    windowMap.put(num, windowMap.getOrDefault(num, 0) + 1);
                    if (windowMap.get(num).equals(targetMap.get(num))) {
                        valid++;
                    }
                }
            }
            while (valid == required) {
                ans = Math.min(ans, r - l + 1);
                for (int i = 0; i < N; i++) {
                    int num = matrix[i][l];
                    if (targetMap.containsKey(num)) {
                        windowMap.put(num, windowMap.getOrDefault(num, 0) - 1);
                        if (windowMap.get(num).equals(targetMap.get(num))) {
                            valid--;
                        }
                    }
                }
                l--;
            }
            r++;
        }
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }
}
