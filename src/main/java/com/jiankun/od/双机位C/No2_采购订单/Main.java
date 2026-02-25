package com.jiankun.od.双机位C.No2_采购订单;

import java.io.BufferedInputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        int n = scanner.nextInt();
        scanner.nextLine();
        List<PR> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str = scanner.nextLine();
            String[] s = str.split(" ");
            PR pr = new PR(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]), Integer.parseInt(s[3]));
            list.add(pr);
        }

        Map<Integer, PR> pdMap = list.stream()
                .filter(pr -> pr.status == 0 && pr.price <= 100)
                .collect(Collectors.toMap(pr -> pr.id, pr -> pr, (pr1, pr2) -> {
                    PR pr = new PR(pr1.id, pr1.number + pr2.number, pr1.price, pr1.status);
                    return pr;
                }));

        List<PR> prList = list.stream().filter(pr -> pr.status == 0 && pr.price > 100).collect(Collectors.toList());

        Set<Map.Entry<Integer, PR>> entries = pdMap.entrySet();
        for (Map.Entry<Integer, PR> entry : entries) {
            prList.add(entry.getValue());
        }

        prList.sort(new Comparator<PR>() {
            @Override
            public int compare(PR o1, PR o2) {
                if (o1.id != o2.id) {
                    return o1.id - o2.id;
                } else {
                    return o2.number - o1.number;
                }
            }
        });

        for (PR pr : prList) {
            if (pr.price >= 100) {
                System.out.println(pr.id + " " + pr.number + " " + pr.price);
            } else {
                if (pr.number < 100) {
                    System.out.println(pr.id + " " + pr.number + " " + pr.price);
                } else {
                    System.out.println(pr.id + " " + pr.number + " " + (int) Math.ceil(pr.price * 0.9));
                }
            }
        }
    }
}

class PR {
    int id;
    int number;
    int price;
    int status;

    public PR(int id, int number, int price, int status) {
        this.id = id;
        this.number = number;
        this.price = price;
        this.status = status;
    }
}
