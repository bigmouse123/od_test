package com.jiankun.test.双机位C;

import java.util.Scanner;

public class Main027 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int K = scanner.nextInt();
        int[] nums = new int[K];
        for (int i = 0; i < K; i++) {
            nums[i] = scanner.nextInt();
        }

        int[] numsMap = new int[1000];
        for (int i = 0; i < K; i++) {
            numsMap[nums[i]]++;
        }

        int[] countMap = new int[1000];
        int l = 0;
        int r = 0;
        int ans = Integer.MAX_VALUE;

        while (r < M) {
            for (int i = 0; i < N; i++) {
                countMap[arr[i][r]]++;
            }
            while (check(countMap, numsMap)) {
                ans = Math.min(ans, r - l + 1);
                for (int i = 0; i < N; i++) {
                    countMap[arr[i][l]]--;
                }
                l++;
            }
            r++;
        }
        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    public static boolean check(int[] countMap, int[] nums) {
        for (int i = 0; i < 1000; i++) {
            if (countMap[i] < nums[i]) {
                return false;
            }
        }
        return true;
    }
}
