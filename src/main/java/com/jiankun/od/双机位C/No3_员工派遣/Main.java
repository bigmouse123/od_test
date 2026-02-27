package com.jiankun.od.双机位C.No3_员工派遣;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int cntx = scanner.nextInt();
        int cnty = scanner.nextInt();

        int k = 1;

        while (true) {
            // 都为0时结束
            if (cntx == 0 && cnty == 0) {
                System.out.println(k - 1);
                break;
            }

            // 当其中一国不需要人而另一国需要人时
            if (cnty == 0) {
                if (k % x != 0) {
                    cntx--;
                }
                k++;
            } else if (cntx == 0) {
                if (k % y != 0) {
                    cnty--;
                }
                k++;
            } else {
                // 以下情况两国都需要人
                if (k % x == 0 && k % y == 0) {

                } else if (k % y == 0) {
                    cntx--;
                } else if (k % x == 0) {
                    cnty--;
                } else {
                    if (cntx >= cnty) {
                        cntx--;
                    } else {
                        cnty--;
                    }
                }
                k++;
            }
        }
    }
}
