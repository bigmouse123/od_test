package com.jiankun.test.双机位C;

import java.util.Arrays;
import java.util.Scanner;

public class Main006 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int minAverageLost = Integer.parseInt(scanner.nextLine());

        int[] nums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringBuilder result = new StringBuilder("NULL");
        int maxLength = 0;
        for (int i = 0; i < nums.length; i++) {
            double currentAverageLost = nums[i];
            int currentLength = 1;
            for (int j = i + 1; j < nums.length; j++) {
                currentAverageLost = (currentAverageLost + nums[j]) / ++currentLength;
                if (currentAverageLost <= minAverageLost) {
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        result = new StringBuilder().append(i).append("-").append(j);
                    } else if (currentLength == maxLength) {
                        result.append(" ").append(i).append("-").append(j);
                    }

                }
            }
        }
        System.out.println(result);

    }
}
