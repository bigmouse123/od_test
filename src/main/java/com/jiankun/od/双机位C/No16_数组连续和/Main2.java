package com.jiankun.od.双机位C.No16_数组连续和;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        if (!scanner.hasNext()) return;

        int n = scanner.nextInt();
        int s = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        long count = 0;

        // preSum[i] 表示前 i 个数的和
        long[] preSum = new long[n + 1];
        preSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int left = 0;
        int right = 1; // preSum 的下标从 1 开始对应第 1 个元素

        if (s == 0) {
            count = (long) n * (n + 1) / 2;
            System.out.println(count);
            return;
        }

        while (right <= n) {
            if (preSum[right] - preSum[left] >= s) {
                // 如果 nums[left...right-1] 满足条件
                // 那么 nums[left...right], nums[left...right+1] ... 肯定都满足
                count += (n - right + 1);

                left++;

                continue;
            }

            right++;

            if (left >= right) {
                right = left + 1;
            }
        }

        System.out.println(count);
    }
}
