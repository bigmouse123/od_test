package com.jiankun.od.双机位C.No3_员工派遣;

import java.util.Scanner;

/**
 * 二分法
 *
 * @author lijiankun
 * @since 2026/2/27
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 使用 long 接收输入，防止溢出
        long x = in.nextLong();
        long y = in.nextLong();
        long cntx = in.nextLong();
        long cnty = in.nextLong();

        long left = cntx + cnty;
        long right = 2000000000L;
        long ans = right;

        while (left <= right) {

            long mid = left + (right - left) / 2;
            if (check(x, y, cntx, cnty, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean check(long x, long y, long cntx, long cnty, long mid) {
        long maxX = mid - mid / x;
        long maxY = mid - mid / y;
        long maxTotal = mid - mid / (x + y);
        return maxX >= cntx && maxY >= cnty && maxTotal >= cntx + cnty;
    }
}
