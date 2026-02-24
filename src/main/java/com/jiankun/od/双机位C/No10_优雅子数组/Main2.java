package com.jiankun.od.双机位C.No10_优雅子数组;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        // 记录滑动窗口内元素的出现次数
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        long ans = 0; // 子数组的数量可能非常大，使用 long 防止溢出

        for (int right = 0; right < n; right++) {
            int val = a[right];
            freq.put(val, freq.getOrDefault(val, 0) + 1);

            // 如果当前元素出现次数达到 k，说明找到了优雅子数组的边界
            // 不断缩小窗口（右移 left），直到窗口内不再包含 k 个 val
            while (freq.get(val) >= k) {
                int leftVal = a[left];
                freq.put(leftVal, freq.get(leftVal) - 1);
                left++;
            }

            // 此时，以当前 right 为结尾，0 到 left-1 为起点的子数组都是优雅子数组
            // 数量正好是 left 个
            ans += left;
        }

        System.out.println(ans);
    }
}
