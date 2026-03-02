package com.jiankun.od.双机位C.No7_竖直四子棋;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int m = scanner.nextInt(); // 宽
        int n = scanner.nextInt(); // 高
        scanner.nextLine();
        int[] line = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] array = new int[n + 1][m + 1];
        boolean flag = false;

        for (int j = 0; j < line.length; j++) {
            if (!flag) {
                if (line[j] >= 1 && line[j] <= m) {
                    if (array[n][line[j]] != 0) {
                        System.out.println(j + 1 + ",error");
                        flag = true;
                        break;
                    }
                    for (int i = 1; i <= n; i++) {
                        if (array[i][line[j]] == 0) {
                            if (j % 2 == 0) {
                                array[i][line[j]] = 1; // 红方
                                if (check(i, line[j], array, m, n)) {
                                    System.out.println(j + 1 + ",red");
                                    flag = true;
                                }
                            } else {
                                array[i][line[j]] = 2; // 蓝方
                                if (check(i, line[j], array, m, n)) {
                                    System.out.println(j + 1 + ",blue");
                                    flag = true;
                                }
                            }
                            break;
                        }
                    }
                } else {
                    System.out.println(j + 1 + ",error");
                    flag = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (!flag) {
            System.out.println("0,draw");
        }
    }

    public static boolean check(int i, int j, int[][] array, int m, int n) {
        // 下
        if (i >= 4 && array[i][j] == array[i - 1][j] && array[i - 1][j] == array[i - 2][j] && array[i - 2][j] == array[i - 3][j]) {
            return true;
        }
        // 左
        if (j >= 4 && array[i][j] == array[i][j - 1] && array[i][j - 1] == array[i][j - 2] && array[i][j - 2] == array[i][j - 3]) {
            return true;
        }
        // 右
        if (j <= m - 3 && array[i][j] == array[i][j + 1] && array[i][j + 1] == array[i][j + 2] && array[i][j + 2] == array[i][j + 3]) {
            return true;
        }
        // 左下
        if (i >= 4 && j >= 4 && array[i][j] == array[i - 1][j - 1] && array[i - 1][j - 1] == array[i - 2][j - 2] && array[i - 2][j - 2] == array[i - 3][j - 3]) {
            return true;
        }
        // 左上
        if (i <= n - 3 && j >= 4 && array[i][j] == array[i + 1][j - 1] && array[i + 1][j - 1] == array[i + 2][j - 2] && array[i + 2][j - 2] == array[i + 3][j - 3]) {
            return true;
        }
        // 右下
        if (i >= 4 && j <= m - 3 && array[i][j] == array[i - 1][j + 1] && array[i - 1][j + 1] == array[i - 2][j + 2] && array[i - 2][j + 2] == array[i - 3][j + 3]) {
            return true;
        }
        // 右上
        if (i <= n - 3 && j <= m - 3 && array[i][j] == array[i + 1][j + 1] && array[i + 1][j + 1] == array[i + 2][j + 2] && array[i + 2][j + 2] == array[i + 3][j + 3]) {
            return true;
        }
        return false;
    }
}
