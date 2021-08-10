package ua.goit.view;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class Util {
    public static <T> String joinListElements(List<T> tList) {
        StringJoiner joiner = new StringJoiner("<br>");
        for (T t : tList) {
            joiner.add(t.toString());
        }
        return joiner.toString();
    }

    public static <T> String joinSetElements(Set<T> tSet) {
        StringJoiner joiner = new StringJoiner("<br>");
        for (T t : tSet) {
            joiner.add(t.toString());
        }
        return joiner.toString();
    }
}
