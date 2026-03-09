package com.jiankun.od.C2025.No36_王者荣耀游戏分组;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int minDiff = Integer.MAX_VALUE;
    static int totalSum = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < array.length; i++) {
            totalSum += array[i];
        }

        findMinDiff(array, 0, 0, 0);
        System.out.println(minDiff);
    }

    /**
     * @param array      评分数组
     * @param index      当前遍历到的队员索引
     * @param count      已选入第一队的队员数量
     * @param currentSum 第一队当前的评分总和
     */
    private static void findMinDiff(int[] array, int index, int count, int currentSum) {
        if (count == 5) {
            int diff = Math.abs(totalSum - 2 * currentSum);
            minDiff = Math.min(minDiff, diff);
            return;
        }
        if (index == 10) {
            return;
        }
        //选择当前队员
        findMinDiff(array, index + 1, count + 1, currentSum + array[index]);
        //不选择当前队员
        findMinDiff(array, index + 1, count, currentSum);
    }
}
