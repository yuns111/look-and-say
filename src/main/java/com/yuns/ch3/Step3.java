package com.yuns.ch3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Step3 {

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


    private static <A> List<List<A>> group(List<A> as) {
        List<List<A>> ass = new ArrayList<>();
        List<A> g = null;

        for (A a : as) {
            if (g == null || !g.get(0).equals(a)) {
                g = new ArrayList<>();
                ass.add(g);
            }
            g.add(a);
        }
        return ass;
    }

    private static <A, B> List<B> map(Function<A, B> func, List<A> as) {
        List<B> bs = new ArrayList<>();
        for (A a : as) {
            bs.add(func.apply(a));
        }
        return bs;
    }

    private static <A> List<A> concat(List<List<A>> ass) {
        List<A> list = new ArrayList<>();
        for (List<A> as : ass) {
            list.addAll(as);
        }

        return list;
    }
}
