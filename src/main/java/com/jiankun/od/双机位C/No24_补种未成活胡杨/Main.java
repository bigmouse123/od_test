package com.jiankun.od.双机位C.No24_补种未成活胡杨;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int arr[] = new int[N + 1];
        for (int i = 0; i < M; i++) {
            arr[scanner.nextInt()] = 1;
        }
        int K = scanner.nextInt();

        int l = 1;
        int r = 1;
        int lSum = 0;
        int rSum = 0;
        int ans = 0;

        while (r <= N) {
            rSum += arr[r];
            while (rSum - lSum > K) {
                lSum += arr[l];
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }

        System.out.println(ans);
    }
}
