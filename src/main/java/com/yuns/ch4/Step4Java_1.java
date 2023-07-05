package com.yuns.ch4;

import java.util.*;

public class Step4Java_1 {

    public static void main(String[] args) {
        Iterator<Integer> result = ant(100);

        while (result.hasNext()) {
            System.out.print(result.next());
        }
    }

    private static Iterator<Integer> ant(int num) {
        Iterator<Integer> result = iter(1);
        for (int i = 0; i < num; i++) {
            result = next(result);
        }
        return result;
    }

    private static Iterator<Integer> next(Iterator<Integer> ns) {
        int count = 1;
        int prev = ns.next();

        List<Integer> list = new ArrayList<>();

        while (ns.hasNext()) {
            int now = ns.next();
            if (prev == now) {
                count++;
            } else {
                list.add(count);
                list.add(prev);

                count = 1;
                prev = now;
            }
        }

        list.add(count);
        list.add(prev);

        return list.iterator();
    }

    private static Iterator<Integer> iter(Integer... values) {
        return Arrays.stream(values).iterator();
    }
}


