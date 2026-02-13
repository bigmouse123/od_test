package com.jiankun.od.双机位C.No12_两个字符串间的最短路径;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// 迪杰斯特拉 + 小顶堆
public class Main {
    // 定义方向：上、下、左、右、左上、右下
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String strA = scanner.next();
        String strB = scanner.next();
        int lengthA = strA.length();
        int lengthB = strB.length();
        System.out.println(dijkstra(strA, strB, lengthA, lengthB));
    }

    public static int dijkstra(String strA, String strB, int lengthA, int lengthB) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[][] dist = new int[lengthA + 1][lengthB + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDist = cur[0];
            int r = cur[1];
            int c = cur[2];
            if (r == lengthA && c == lengthB) {
                return curDist;
            }
            if (curDist > dist[r][c]) {
                continue;
            }
            for (int i = 0; i < DIRECTIONS.length; i++) {
                int nr = r + DIRECTIONS[i][0];
                int nc = c + DIRECTIONS[i][1];
                if (nr >= 0 && nr <= lengthA && nc >= 0 && nc <= lengthB) {
                    if (i == 4) {
                        if (strA.charAt(nr) != strB.charAt(nc)) {
                            continue;
                        }
                    }
                    if (i == 5) {
                        if (strA.charAt(r) != strB.charAt(c)) {
                            continue;
                        }
                    }
                    int newDist = curDist + 1;
                    if (newDist < dist[nr][nc]) {
                        dist[nr][nc] = newDist;
                        pq.offer(new int[]{newDist, nr, nc});
                    }
                }
            }
        }
        return -1;
    }
}
