package com.jiankun.od.查找接口成功率最优时间段;

import java.util.Arrays;
import java.util.Scanner;

// 查找接口成功率最优时间段
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int minAverageLost = Integer.parseInt(scanner.nextLine());
        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxLength = 0;
        StringBuilder result = new StringBuilder("NULL");
        for (int i = 0; i < array.length; i++) {
            int currentLength = 1;
            double sumAverageLost = array[i];
            for (int j = i + 1; j < array.length; j++) {
                sumAverageLost = sumAverageLost + array[j];
                currentLength++;
                double averageLost = sumAverageLost / currentLength;
                if (averageLost <= minAverageLost) {
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        result = new StringBuilder(i + "-" + j);
                    } else if (currentLength == maxLength) {
                        result.append(" ").append(i).append("-").append(j);
                    }
                }
            }
        }
        System.out.println(result);
    }
}
