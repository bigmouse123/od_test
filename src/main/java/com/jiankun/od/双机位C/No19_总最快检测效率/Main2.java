package com.jiankun.od.双机位C.No19_总最快检测效率;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int peopleNum = scanner.nextInt();
        int volunteerNum = scanner.nextInt();

        int[] xiaolu = new int[peopleNum];
        int[] M = new int[peopleNum];
        for (int i = 0; i < peopleNum; i++) {
            xiaolu[i] = scanner.nextInt();
            M[i] = xiaolu[i] / 10;
        }

        if (volunteerNum >= 4 * peopleNum) {
            int result = Arrays.stream(xiaolu).sum() + 3 * Arrays.stream(M).sum();
            System.out.println(result);
            return;
        }

        int[][] dp = new int[peopleNum + 1][volunteerNum + 1];
        // dp[i][j] 表示前i个采样员在有j个志愿者的情况下的最大效率

        int count = 0;
        for (int i = 1; i <= peopleNum; i++) {
            count += xiaolu[i - 1] - 2 * M[i - 1];
            dp[i][0] = count;
        }

        // 初始化
        for (int j = 1; j <= volunteerNum; j++) {
            if (j == 1) {
                dp[1][j] = xiaolu[0];
            } else if (j == 2) {
                dp[1][j] = xiaolu[0] + M[0];
            } else if (j == 3) {
                dp[1][j] = xiaolu[0] + 2 * M[0];
            } else if (j == 4) {
                dp[1][j] = xiaolu[0] + 3 * M[0];
            } else {
                dp[1][j] = dp[1][4];
            }
        }

        // dp[i][j] 表示前i个采样员在有j个志愿者的情况下的最大效率
        // 若第i个人有0个志愿者，则前i-1个人最大效率为dp[i-1][j]
        // 若第i个人有1个志愿者，则前i-1个人最大效率为dp[i-1][j-1]
        // ...
        // 若第i个人有4个志愿者，则前i-1个人最大效率为dp[i-1][j-4]
        for (int i = 2; i <= peopleNum; i++) {
            for (int j = 1; j <= volunteerNum; j++) {
                int result0 = dp[i - 1][j] + xiaolu[i - 1] - 2 * M[i - 1];
                int result1 = dp[i - 1][j - 1] + xiaolu[i - 1];
                dp[i][j] = Math.max(result0, result1);
                if (j == 2) {
                    int result2 = dp[i - 1][j - 2] + xiaolu[i - 1] + M[i - 1];
                    dp[i][j] = Math.max(dp[i][j], result2);
                }
                if (j >= 3) {
                    int result3 = dp[i - 1][j - 3] + xiaolu[i - 1] + 2 * M[i - 1];
                    dp[i][j] = Math.max(dp[i][j], result3);
                }
                if (j >= 4) {
                    int result4 = dp[i - 1][j - 4] + xiaolu[i - 1] + 3 * M[i - 1];
                    dp[i][j] = Math.max(dp[i][j], result4);
                }
            }
        }

        System.out.println(dp[peopleNum][volunteerNum]);


    }
}
