package com.jiankun.od.双机位C.No27_最小矩阵宽度;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int K = scanner.nextInt();
        int[] nums = new int[K];
        for (int i = 0; i < K; i++) {
            int val = scanner.nextInt();
            nums[i] = val;
        }

        int[][] count_map = new int[M][1000];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                count_map[j][matrix[i][j]]++;
            }
        }

        int[] target_map = new int[1000];
        for (int i = 0; i < K; i++) {
            target_map[nums[i]]++;
        }

        int[] cul_count_map = new int[1000];
        int l = 0;
        int r = 0;
        int ans = Integer.MAX_VALUE;
        while (r < M) {
            for (int i = 0; i < N; i++) {
                cul_count_map[matrix[i][r]]++;
            }
            while (check(cul_count_map, target_map)) {
                ans = Math.min(ans, r - l + 1);
                for (int i = 0; i < N; i++) {
                    cul_count_map[matrix[i][l]]--;
                }
                l++;
            }
            r++;
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static boolean check(int[] cul_count_map, int[] target_map) {
        for (int i = 0; i < 1000; i++) {
            if (cul_count_map[i] < target_map[i]) {
                return false;
            }
        }
        return true;
    }
}
