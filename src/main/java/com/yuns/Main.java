package com.yuns;

public class Main {

    public static void main(String... args) {
        System.out.println(lookAndSay(10));
    }

    private static String lookAndSay(int num) {
        String s = "1";
        for (int line = 0; line < num; line++) {
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
            s = result;
        }
        return s;
    }
}
