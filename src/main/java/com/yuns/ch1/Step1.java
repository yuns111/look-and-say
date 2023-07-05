package com.yuns.ch1;

public class Step1 {

    public static void main(String[] args) {
        System.out.println(ant(10));
    }

    private static String ant(int num) {

        String s = "1";
        for (int line = 0; line < num; line++) {
            s = next(s);
        }

        return s;
    }

    private static String next(String s) {
        int length = 1;
        char head = s.charAt(0);
        String result = "";

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == head) {
                length++;
            } else {
                result += length;
                result += head;
                length = 1;
                head = s.charAt(i);
            }
        }

        result += length;
        result += head;

        return result;
    }
}
