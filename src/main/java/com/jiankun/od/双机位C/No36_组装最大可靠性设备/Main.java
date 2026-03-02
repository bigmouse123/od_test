package com.jiankun.od.双机位C.No36_组装最大可靠性设备;

import java.io.BufferedInputStream;
import java.util.*;
import java.util.stream.Collectors;

// 二分法
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        String[] split = scanner.nextLine().split(" ");
        int s = Integer.parseInt(split[0]); // 总的预算
        int n = Integer.parseInt(split[1]); // 需要的元器件种类

        int total = Integer.parseInt(scanner.nextLine()); // 有total行数据
        ArrayList<Device> list = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Device device = new Device(array[0], array[1], array[2]);
            list.add(device);
        }
        Map<Integer, List<Device>> map = list.stream().collect(Collectors.groupingBy(device -> device.id));

        // 二分查找确认最小可靠性
        int left = 0;
        int right = 100000;
        int ans = -1;

        while (left <= right) {
            int mid = (left + right) / 2; // mid表示可靠性
            if (check(mid, map, s, n)) {
                left = mid + 1;
                ans = mid;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(ans);

    }

    /**
     * @param mid 可靠性
     * @param map 所有元器件按照类别分类
     * @param s   预算
     * @param n   需要的元器件总数
     * @return
     */
    public static boolean check(int mid, Map<Integer, List<Device>> map, int s, int n) {
        // 遍历所有类型的元器件，共n个，计算出最低花费
        int totalCost = 0;
        for (Map.Entry<Integer, List<Device>> entry : map.entrySet()) {
            // 遍历每类元器件的所有元器件，找到符合可靠性条件的预算最低的元器件
            int minValue = Integer.MAX_VALUE;
            for (Device device : entry.getValue()) {
                if (device.reliability >= mid) { // 如果当前元器件的可靠性达到了目标可行性
                    minValue = Math.min(minValue, device.price);
                }
            }
            if (minValue == Integer.MAX_VALUE) {
                return false;
            }
            totalCost += minValue;
            if (totalCost > s) {
                return false;
            }
        }
        return true;
    }
}

class Device {
    int id; // 编号
    int reliability; // 可靠性
    int price; // 价格

    public Device(int id, int reliability, int price) {
        this.id = id;
        this.reliability = reliability;
        this.price = price;
    }
}
