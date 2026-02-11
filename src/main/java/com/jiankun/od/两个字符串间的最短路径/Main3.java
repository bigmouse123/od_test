package com.jiankun.od.两个字符串间的最短路径;

import java.util.Scanner;
import java.util.*;

// BFS
public class Main3 {
    // 使用一个静态全局变量来存储和更新找到的最小路径长度
    public static int result = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // --- 1. 输入处理 ---
        Scanner in = new Scanner(System.in);
        String[] input_strs = in.nextLine().split(" ");
        char[] str1 = input_strs[0].toCharArray();
        char[] str2 = input_strs[1].toCharArray();
        int n = str1.length;
        int m = str2.length;

        // --- 2. 初始化访问过的节点记录 ---
        // `visited` 数组用于记录网格中的某个点 (i, j) 是否已经被访问过，防止重复计算和无限循环。
        // 数组大小为 (n+2) x (m+2) 是为了处理坐标系偏移，避免边界检查。
        int[][] visited = new int[n + 2][m + 2];
        for (int i = 0; i <= n + 1; i++) {
            for (int j = 0; j <= m + 1; j++) {
                visited[i][j] = 0; // 0 表示未访问
            }
        }

        // --- 3. 初始化广度优先搜索 (BFS) 队列 ---
        // 使用一个链表作为队列来进行BFS。
        LinkedList<int[]> lst = new LinkedList<>();
        // 队列中的每个元素是一个数组，代表一个状态: {x坐标, y坐标, 到达该点的距离}
        // 坐标系被设计为从 (-1, -1) 开始，代表原点 (0,0) 的“前一个”位置。
        // 初始状态：在原点之前，坐标为(-1, -1)，距离为0。
        lst.offer(new int[]{-1, -1, 0});

        // --- 4. 执行广度优先搜索 ---
        while (true) {
            // 如果队列为空，说明所有可达的路径都已探索完毕。
            if (lst.size() <= 0) {
                System.out.println(result); // 输出全局记录的最小值
                break;
            } else {
                // 取出队列头部的状态进行处理
                int[] current = lst.poll();

                // --- 5. 终点判断与结果更新 ---
                // 这里的逻辑很特别：它不是在到达终点(n,m)时停止，
                // 而是在到达最后一行或最后一列的任意点时，计算一个“候选”总距离。
                // BFS保证了第一次到达某行/列的路径是到该行/列的最短路径。

                // 如果当前节点的下一步将进入最后一行 (即到达 (n-1, y))
                if (current[0] + 1 == n) {
                    // 计算总距离：当前路径长度 + 从当前y坐标垂直走到终点y坐标的距离。
                    // current[2] - current[1] + m - 1  等价于 current[2] + (m - (current[1] + 1))
                    int potential_result = current[2] + (m - 1 - current[1]);
                    if (potential_result < result) {
                        result = potential_result; // 更新全局最小值
                    }
                    continue; // 继续处理队列中的其他路径，因为它们可能通向更优的解
                }

                // 如果当前节点的下一步将进入最后一列 (即到达 (x, m-1))
                if (current[1] + 1 == m) {
                    // 计算总距离：当前路径长度 + 从当前x坐标水平走到终点x坐标的距离。
                    int potential_result = current[2] + (n - 1 - current[0]);
                    if (potential_result < result) {
                        result = potential_result; // 更新全局最小值
                    }
                    continue;
                }

                // --- 6. 探索下一个节点 ---
                int next_x = current[0] + 1;
                int next_y = current[1] + 1;

                // 如果两个字符串在下一个位置的字符相同，则可以走斜边
                if (str1[next_x] == str2[next_y]) {
                    // 检查斜向的下一个节点是否已访问
                    // 注意：visited数组的索引是实际坐标+2
                    if (visited[next_x + 2][next_y + 2] == 0) {
                        visited[next_x + 2][next_y + 2] = 1; // 标记为已访问
                        // 将新状态加入队列：坐标更新，距离+1
                        lst.offer(new int[]{next_x, next_y, current[2] + 1});
                    }
                } else {
                    // 如果字符不同，只能走水平或垂直边
                    // 探索水平移动
                    if (visited[next_x + 2][current[1] + 2] == 0) {
                        visited[next_x + 2][current[1] + 2] = 1;
                        lst.offer(new int[]{next_x, current[1], current[2] + 1});
                    }
                    // 探索垂直移动
                    if (visited[current[0] + 2][next_y + 2] == 0) {
                        visited[current[0] + 2][next_y + 2] = 1;
                        lst.offer(new int[]{current[0], next_y, current[2] + 1});
                    }
                }
            }
        }
    }

}
