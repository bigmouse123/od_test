package com.jiankun.leetcode;

public class LeetCode_1004 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1};
        int k = 1;
        new Solution().longestOnes(nums, k);
    }
}

class Solution {
    // 暴力解(超时)
    public int longestOnes(int[] nums, int k) {
        int maxCount = 0;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            int tempK = k;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 1) {
                    count++;
                } else {
                    if (tempK > 0) {
                        count++;
                        tempK--;
                    } else {
                        maxCount = Math.max(maxCount, count);
                        j--;
                        tempK = k;
                        count = 0;
                    }
                }
            }
            if (tempK >= 0) {
                maxCount = Math.max(maxCount, count);
            }
        }
        return maxCount;
    }

    // 滑动窗口
    public int longestOnes2(int[] nums, int k) {
        int l = 0;
        int lSum = 0;
        int rSum = 0;
        int r = 0;
        int ans = 0;
        while (r < nums.length) {
            rSum += 1 - nums[r];
            while (rSum - lSum > k) {
                lSum += 1 - nums[l];
                l++;
            }
            ans = Math.max(ans, r - l + 1);
            r++;
        }
        return ans;
    }
}
