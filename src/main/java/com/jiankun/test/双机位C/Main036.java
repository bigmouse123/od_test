package com.jiankun.test.双机位C;

import java.util.*;
import java.util.stream.Collectors;

public class Main036 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int S = scanner.nextInt(); // 总预算
        int N = scanner.nextInt(); // 元器件种类
        int total = scanner.nextInt(); // 元器件总行数
        scanner.nextLine();
        ArrayList<Device> deviceList = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            deviceList.add(new Device(array[0], array[1], array[2]));
        }
        Map<Integer, List<Device>> deviceMap = deviceList.stream().collect(Collectors.groupingBy(device -> device.type));

        int l = 0;
        int r = 100000;
        int ans = -1;
        while (l <= r) {
            int mid = (l + r) / 2; // 可靠性
            int totalPrice = 0;
            boolean flag = false;
            for (Map.Entry<Integer, List<Device>> entry : deviceMap.entrySet()) {
                int minPrice = Integer.MAX_VALUE;
                for (Device device : entry.getValue()) {
                    if (device.reliability >= mid) {
                        minPrice = Math.min(minPrice, device.price);
                    }
                }
                if (minPrice == Integer.MAX_VALUE) {
                    r = mid - 1;
                    flag = true;
                    break;
                }
                totalPrice += minPrice;
            }
            if (flag) {
                continue;
            }
            if (totalPrice > S) {
                r = mid - 1;
            } else {
                l = mid + 1;
                ans = mid;
            }
        }
        System.out.println(ans);
    }
}

class Device {
    int type;
    int reliability;
    int price;

    public Device(int type, int reliability, int price) {
        this.type = type;
        this.reliability = reliability;
        this.price = price;
    }
}
