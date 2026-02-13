package com.jiankun.od.双机位C.No10_优雅子数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (j - i + 1 < k) {
                    continue;
                }
                // 数组转集合
                int[] subArray = Arrays.copyOfRange(nums, i, j + 1);
                ArrayList<Integer> arrayList = new ArrayList<>();
                for (int s : subArray) {
                    arrayList.add(s);
                }
                Map<Integer, Integer> map = arrayList.stream().collect(Collectors.toMap(x -> x, x -> 1, Integer::sum));
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    Integer value = entry.getValue();
                    if (value >= k) {
                        count++;
                        break;
                    }
                }
            }
        }

        System.out.println(count);

    }
}
