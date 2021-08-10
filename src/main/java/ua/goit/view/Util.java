package ua.goit.view;

import java.util.Set;
import java.util.StringJoiner;

public class Util {
    public static <T> String joinSetElements(Set<T> tSet) {
        StringJoiner joiner = new StringJoiner("<br>");
        for (T t : tSet) {
            joiner.add(t.toString());
        }
        return joiner.toString();
    }
}
