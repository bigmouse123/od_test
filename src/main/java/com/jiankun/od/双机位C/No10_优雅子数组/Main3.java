package com.jiankun.od.双机位C.No10_优雅子数组;

import java.util.Scanner;
import java.util.*;

class Main3 {
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        //转为数组
        int A = in.nextInt();
        int k = in.nextInt();
        //转为数组
        int[] nums = new int[A];
        for (int i = 0; i < A; i++) {
            nums[i] = in.nextInt();
        }

        int result = 0;

        int left = 0;
        int right = 0;
        HashMap<Integer, Integer> num_count = new HashMap<>();

        while (left < A && right < A) {
            Integer c = nums[right];
            num_count.put(c, num_count.getOrDefault(c, 0) + 1);
            if (num_count.get(c) >= k) {
                result += A - right;

                num_count.put(nums[left], num_count.get(nums[left]) - 1);
                left++;

                num_count.put(c, num_count.get(c) - 1);
                right--;
            }
            right++;
        }
        System.out.println(result);
    }
}
