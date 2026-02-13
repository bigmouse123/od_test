package com.jiankun.od.双机位C.No16_数组连续和;

import java.io.BufferedInputStream;
import java.util.Scanner;

// gemini
public class Main3 {
    public static void main(String[] args) {
        // 使用 BufferedInputStream 加速输入读取
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));

        if (scanner.hasNext()) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = scanner.nextInt();
            }

            // 注意：结果可能很大，N*(N+1)/2 可能超过 int 范围，建议使用 long
            long count = 0;
            int sum = 0;
            int left = 0;
            int right = 0;

            // 滑动窗口 O(N)
            while (right < n) {
                // 进窗口
                sum += nums[right];

                // 当窗口和大于等于 x 时，尝试收缩窗口
                while (sum >= x) {
                    // 核心逻辑：
                    // 如果 nums[left...right] 的和 >= x
                    // 那么以 left 开头，结尾为 right, right+1 ... n-1 的所有区间都满足条件
                    // 个数共有 n - right 个
                    count += (n - right);

                    // 出窗口：尝试缩小左边界，看是否依然满足
                    sum -= nums[left];
                    left++;
                }

                // 继续扩大右边界
                right++;
            }

            System.out.println(count);
        }
        scanner.close();
    }
}
