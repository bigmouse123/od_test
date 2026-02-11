package com.jiankun.od.两个字符串间的最短路径;

import java.util.Scanner;

// 动态规划
public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String A = scanner.next();
            String B = scanner.next();

            int m = A.length();
            int n = B.length();

            // 空间优化：确保 A 是较长的字符串，B 是较短的字符串
            if (m < n) {
                String tempStr = A;
                A = B;
                B = tempStr;
                int tempLen = m;
                m = n;
                n = tempLen;
            }

            int[] dp = new int[n + 1];

            for (int i = 1; i <= m; i++) {
                int prev = 0; // 相当于 dp[i-1][j-1]
                for (int j = 1; j <= n; j++) {
                    int temp = dp[j]; // 保存当前位置旧值

                    if (A.charAt(i - 1) == B.charAt(j - 1)) {
                        dp[j] = prev + 1;
                    } else {
                        dp[j] = Math.max(dp[j], dp[j - 1]);
                    }

                    prev = temp;
                }
            }

            int shortestDistance = m + n - dp[n];
            System.out.println(shortestDistance);
        }
        scanner.close();
    }
}
