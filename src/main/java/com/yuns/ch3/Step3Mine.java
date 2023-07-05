package com.yuns.ch3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Step3Mine {

    public static void main(String[] args) {
        System.out.println(ant(10));
    }

    private static List<Integer>  ant(int num) {
        List<Integer> s = Arrays.asList(1);
        for (int i = 0; i < num; i++) {
            s = next(s);
        }
        return s;
    }

    private static List<Integer> next(List<Integer> ns) {
        return concat(map(g -> Arrays.asList(g.size(), g.get(0)), group(ns)));
    }

    private static List<List<Integer>> group(List<Integer> as) {
        List<List<Integer>> ass = new ArrayList<>();
        List<Integer> g = null;

        for (Integer a : as) {
            if (g == null || !g.get(0).equals(a)) {
                g = new ArrayList<>();
                ass.add(g);
            }
            g.add(a);
        }
        return ass;
    }

    private static List<List<Integer>> map(Function<List<Integer>, List<Integer>> func, List<List<Integer>> as) {
        List<List<Integer>> bs = new ArrayList<>();
        for (List<Integer> a : as) {
            bs.add(func.apply(a));
        }
        return bs;
    }

    private static List<Integer> concat(List<List<Integer>> ass) {
        List<Integer> list = new ArrayList<>();
        for (List<Integer> as : ass) {
            list.addAll(as);
        }

        return list;
    }
}
