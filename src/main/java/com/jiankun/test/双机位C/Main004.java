package com.jiankun.test.双机位C;

import java.util.Arrays;
import java.util.Scanner;

public class Main004 {
    static int n;
    static int t;
    static int k;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt(); // 可选运动数
        t = scanner.nextInt(); // 选取的运动卡路里之和
        k = scanner.nextInt(); // 选取的运动
        scanner.nextLine();
        // 每项运动消耗的卡路里
        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(dfs(array, 0, 0, 0));

    }

    public static int dfs(int[] array, int currentT, int currentK, int index) {
        if (currentK == k) {
            return currentT == t ? 1 : 0;
        }
        if (index >= array.length) {
            return 0;
        }
        if (currentK < k && currentT >= t) {
            return 0;
        }
        // 选取当前运动
        int count = dfs(array, currentT + array[index], currentK + 1, index + 1);

        // 不算当前运动
        count += dfs(array, currentT, currentK, index + 1);

        return count;
    }
}
