package com.yuns.ch5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Step5Java_2 {

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


    private static Iterator<Integer> next(Iterator<Integer> prev) {
        return new Concat(new Map(g -> Arrays.asList(g.size(), g.get(0)).iterator(), new Group(prev)));
    }

    private static class Group implements Iterator<List<Integer>> {
        private Iterator<Integer> target;
        private List<Integer> grouping;

        private boolean hasNext;
        private Integer before;

        public Group(Iterator<Integer> target) {
            this.target = target;
            this.hasNext = target.hasNext();
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public List<Integer> next() {
            grouping = new ArrayList<>();

            before = before == null ? target.next() : before;
            grouping.add(before);

            hasNext = target.hasNext();
            while (hasNext) {
                int now = target.next();

                if (now == before) {
                    grouping.add(now);
                } else {
                    before = now;
                    break;
                }

                hasNext = target.hasNext();
            }

            return grouping;
        }
    }

    private static class Map implements Iterator<Iterator<Integer>> {
        private Function<List<Integer>, Iterator<Integer>> func;
        private Iterator<List<Integer>> grouping;

        public Map(Function<List<Integer>, Iterator<Integer>> func, Iterator<List<Integer>> grouping) {
            this.func = func;
            this.grouping = grouping;
        }

        @Override
        public boolean hasNext() {
            return grouping.hasNext();
        }

        @Override
        public Iterator<Integer> next() {
            List<Integer> intList = grouping.next();
            return func.apply(intList);
        }
    }

    private static class Concat implements Iterator<Integer> {
        private Iterator<Iterator<Integer>> mapping;
        private Iterator<Integer> inner;

        private boolean hasNext;
        private boolean targetHasNext;

        public Concat(Iterator<Iterator<Integer>> mapping) {
            this.mapping = mapping;
            this.targetHasNext = mapping.hasNext();
            this.hasNext = this.targetHasNext;
        }

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public Integer next() {
            int result = 0;

            if (inner == null) {
                inner = mapping.next();
            }

            if (inner != null) {
                boolean resNext = inner.hasNext();

                if (resNext) {
                    result = inner.next();
                } else if (mapping.hasNext()) {
                    inner = mapping.next();
                    result = inner.next();
                }

                hasNext = targetHasNext;
            }

            targetHasNext = mapping.hasNext();

            return result;
        }
    }

    private static Iterator<Integer> iter(Integer... values) {
        return Arrays.stream(values).iterator();
    }
}


