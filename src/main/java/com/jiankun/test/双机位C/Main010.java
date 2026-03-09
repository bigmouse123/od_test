package com.jiankun.test.双机位C;

import java.util.HashMap;
import java.util.Scanner;

public class Main010 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        int l = 0;
        int r = 0;
        long count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        while (r < n) {
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);
            while (map.get(nums[r]) >= k) {
                map.put(nums[l], map.get(nums[l]) - 1);
                l++;
            }
            count += l;
            r++;
        }

        System.out.println(count);
    }
}
