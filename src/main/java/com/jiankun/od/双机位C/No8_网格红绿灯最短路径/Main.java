package com.jiankun.od.双机位C.No8_网格红绿灯最短路径;

import java.util.*;

public class Main {
    // 定义方向：上、下、左、右
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. 读取并解析 grids
        String gridsStr = scanner.nextLine();
        int[][] grids = parseArray(gridsStr);

        // 2. 读取并解析 lights
        String lightsStr = scanner.nextLine();
        int[][] lightsArr = parseArray(lightsStr);

        // 3. 将 lights 转换为更易查询的 map 或二维数组
        // 由于 grid 只有 100x100，可以直接用一个 waitTimes 数组
        int m = grids.length;
        int n = grids[0].length;
        int[][] waitTimes = new int[m][n];
        for (int[] light : lightsArr) {
            int r = light[0];
            int c = light[1];
            int wait = light[2];
            if (r >= 0 && r < m && c >= 0 && c < n) {
                waitTimes[r][c] = wait;
            }
        }

        // 4. 执行 Dijkstra 算法
        System.out.println(dijkstra(grids, waitTimes, m, n));
    }

    private static int dijkstra(int[][] grids, int[][] waitTimes, int m, int n) {
        // 边界检查：如果起点或终点是障碍物
        if (grids[0][0] == 1 || grids[m - 1][n - 1] == 1) {
            return -1;
        }

        // 优先队列：int[] {当前耗时, r, c}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // 距离数组，初始化为 MAX_VALUE
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // 如果起点是红绿灯，初始时间不是 0，而是起点的等待时间
        int initialWait = 0;
        if (grids[0][0] == 2) {
            initialWait = waitTimes[0][0];
        }
        // 起点初始化
        pq.offer(new int[]{initialWait, 0, 0});
        dist[0][0] = initialWait;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int r = current[1];
            int c = current[2];

            // 如果到达终点，直接返回结果
            if (r == m - 1 && c == n - 1) {
                return time;
            }

            // 如果当前路径时间已经大于已知最短时间，跳过
            if (time > dist[r][c]) {
                continue;
            }

            // 遍历四个方向
            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // 检查边界和障碍物
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grids[nr][nc] != 1) {
                    // 基础移动耗时 1 秒
                    int moveCost = 1;
                    // 等待时间
                    int waitCost = (grids[nr][nc] == 2) ? waitTimes[nr][nc] : 0;

                    int newTime = time + moveCost + waitCost;

                    // 如果找到更短路径，更新并入队
                    if (newTime < dist[nr][nc]) {
                        dist[nr][nc] = newTime;
                        pq.offer(new int[]{newTime, nr, nc});
                    }
                }
            }
        }

        return -1; // 无法到达
    }

    // 辅助方法：解析字符串 [[1,2],[3,4]] 为二维数组
    private static int[][] parseArray(String str) {
        // 去除外层的 [[ 和 ]]，或者空字符串处理
        if (str == null || str.length() <= 4) {
            return new int[0][0];
        }

        // 简单解析逻辑：
        // 1. 去掉首尾的 [ ]
        String content = str.substring(1, str.length() - 1);
        // 2. 按照 "],[" 分割行
        // 注意：正则中 [ 需要转义
        String[] rows = content.split("\\],\\[");

        int rowCount = rows.length;
        // 先解析第一行确定列数（假设矩阵规则）
        String[] firstRowCols = rows[0].replaceAll("[\\[\\]]", "").split(",");
        int colCount = firstRowCols.length;

        int[][] result = new int[rowCount][colCount];

        for (int i = 0; i < rowCount; i++) {
            String rowStr = rows[i].replaceAll("[\\[\\]]", "");
            String[] nums = rowStr.split(",");
            for (int j = 0; j < nums.length; j++) {
                result[i][j] = Integer.parseInt(nums[j].trim());
            }
        }
        return result;
    }
}
