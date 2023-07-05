package com.yuns.ch2;

import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Step2 {

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
        return replace(s, "(.)\\1*", m -> m.group().length() + m.group(1));
    }

    private static String replace(String target, String regex, Function<MatchResult, String> fn) {
        StringBuffer sb = new StringBuffer();
        Matcher m = Pattern.compile(regex).matcher(target);

        while (m.find()) {
            m.appendReplacement(sb, fn.apply(m));
        }
        m.appendTail(sb);

        return sb.toString();
    }
}
