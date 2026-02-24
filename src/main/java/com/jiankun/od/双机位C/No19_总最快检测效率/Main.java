package com.jiankun.od.双机位C.No19_总最快检测效率;

import java.util.*;

// 动态规划
public class Main {
    public static void main(String[] args) {
        // 输入处理
        Scanner in = new Scanner(System.in);
        // 采样员人数
        int sample_num = in.nextInt();
        // 志愿者人数
        int volunteer_num = in.nextInt();

        // 各采样员的基准效率 N
        Integer[] efficiencys = new Integer[sample_num];
        for (int i = 0; i < sample_num; i++) {
            efficiencys[i] = in.nextInt();
        }

        // 计算每个采样员的效率浮动粒度 M (M = N * 10%)
        int[] range_efficiencys = new int[sample_num];
        for (int i = 0; i < sample_num; i++) {
            range_efficiencys[i] = efficiencys[i] / 10;
        }

        // 动态规划求解
        // dp[i][j] 表示考虑前 i 个采样员，分配 j 个志愿者时能达到的最大总效率
        // dp 数组大小为 (采样员数+1) x (志愿者数+1) 以方便处理边界情况
        int[][] dp = new int[sample_num + 1][volunteer_num + 1];

        // 初始化：当没有志愿者(j=0)时的情况
        // 如果一个志愿者都没有，则所有采样员的效率都下降 2M
        int count = 0;
        for (int i = 1; i < sample_num + 1; i++) {
            // 计算前 i 个采样员在没有志愿者的情况下的效率总和
            count += (efficiencys[i - 1] - 2 * range_efficiencys[i - 1]);
            dp[i][0] = count;
        }

        // 状态转移：遍历每个采样员和每个可能的志愿者数量
        for (int i = 1; i < sample_num + 1; i++) {  // i 代表当前正在考虑第 i 个采样员 (对应数组下标 i-1)
            for (int j = 1; j < volunteer_num + 1; j++) { // j 代表当前总共可以使用的志愿者数量

                // 核心状态转移方程：
                // 对于第 i 个采样员，我们可以选择分配不同数量的志愿者给他/她。
                // dp[i][j] 的值取决于不分配给第 i 个采样员，或者分配1,2,3,4个志愿者给他的最优解。

                // --- 决策 1: 给第 i 个采样员分配 0 个志愿者 ---
                // 效率 = (前 i-1 个采样员用 j 个志愿者的最大效率) + (第 i 个采样员效率下降2M)
                int choice0 = dp[i - 1][j] + efficiencys[i - 1] - 2 * range_efficiencys[i - 1];

                // --- 决策 2: 给第 i 个采样员分配 1 个志愿者 (需要 j>=1) ---
                // 效率 = (前 i-1 个采样员用 j-1 个志愿者的最大效率) + (第 i 个采样员正常效率)
                int choice1 = dp[i - 1][j - 1] + efficiencys[i - 1];

                // 比较分配 0 个和 1 个志愿者的优劣
                dp[i][j] = Math.max(choice0, choice1);

                // --- 决策 3: 给第 i 个采样员分配 2 个志愿者 (需要 j>=2) ---
                // 效率 = (前 i-1 个采样员用 j-2 个志愿者的最大效率) + (第 i 个采样员效率提升1M)
                if (j >= 2) {
                    int choice2 = dp[i - 1][j - 2] + efficiencys[i - 1] + range_efficiencys[i - 1];
                    dp[i][j] = Math.max(dp[i][j], choice2);
                }

                // --- 决策 4: 给第 i 个采样员分配 3 个志愿者 (需要 j>=3) ---
                // 效率 = (前 i-1 个采样员用 j-3 个志愿者的最大效率) + (第 i 个采样员效率提升2M)
                if (j >= 3) {
                    int choice3 = dp[i - 1][j - 3] + efficiencys[i - 1] + 2 * range_efficiencys[i - 1];
                    dp[i][j] = Math.max(dp[i][j], choice3);
                }

                // --- 决策 5: 给第 i 个采样员分配 4 个志愿者 (需要 j>=4) ---
                // 效率 = (前 i-1 个采样员用 j-4 个志愿者的最大效率) + (第 i 个采样员效率提升3M)
                if (j >= 4) {
                    int choice4 = dp[i - 1][j - 4] + efficiencys[i - 1] + 3 * range_efficiencys[i - 1];
                    dp[i][j] = Math.max(dp[i][j], choice4);
                }
            }
        }

        // 最终结果存储在 dp[sample_num][volunteer_num] 中，
        // 它表示考虑了所有采样员，并用完了所有志愿者时的最优解。
        System.out.println(dp[sample_num][volunteer_num]);
    }
}
