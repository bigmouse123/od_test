package com.jiankun.test.双机位C;

import java.io.BufferedInputStream;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main008 {
    // 定义方向 上下左右
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        String gridStr = scanner.nextLine();
        String lightStr = scanner.nextLine();

        int[][] grids = parseArray(gridStr);
        int[][] lights = parseArray(lightStr);

        int m = grids.length;
        int n = grids[0].length;

        int[][] waitTimes = new int[m][n];
        for (int[] light : lights) {
            waitTimes[light[0]][light[1]] = light[2];
        }

        System.out.println(djs(grids, waitTimes, m, n));


    }


    public static int djs(int[][] grids, int[][] waitTimes, int m, int n) {
        if (grids[0][0] == 1 || grids[m - 1][n - 1] == 1) {
            return -1;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int[][] dist = new int[m][n];
        for (int[] ints : dist) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }

        int initWait = 0;
        if (grids[0][0] == 2) {
            initWait = waitTimes[0][0];
        }
        queue.offer(new int[]{initWait, 0, 0});
        dist[0][0] = initWait;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int time = current[0];
            int r = current[1];
            int c = current[2];

            if (r == m - 1 && c == n - 1) {
                return time;
            }

            if (time > dist[r][c]) {
                continue;
            }

            for (int[] direction : DIRECTIONS) {
                int nr = r + direction[0];
                int nc = c + direction[1];

                if (nr >= 0 && nr <= m - 1 && nc >= 0 && nc <= n - 1 && grids[nr][nc] != 1) {
                    int moveCost = 1;
                    int waitCost = 0;
                    if (grids[nr][nc] == 2) {
                        waitCost = waitTimes[nr][nc];
                    }
                    int totalCost = moveCost + waitCost + time;
                    if (totalCost < dist[nr][nc]) {
                        dist[nr][nc] = totalCost;
                        queue.offer(new int[]{totalCost, nr, nc});
                    }
                }
            }
        }
        return -1;
    }

    public static int[][] parseArray(String string) {
        if (string == null || string.length() <= 4) {
            return new int[0][0];
        }
        String[] split = string.substring(1, string.length() - 1).split("],\\[");
        int length = split[0].replaceAll("[\\[\\]]", "").split(",").length;
        int[][] array = new int[split.length][length];
        for (int i = 0; i < split.length; i++) {
            int[] ints = Arrays.stream(split[i].replaceAll("[\\[\\]]", "").split(",")).mapToInt(Integer::parseInt).toArray();
            array[i] = Arrays.copyOf(ints, ints.length);
        }
        return array;
    }
}
