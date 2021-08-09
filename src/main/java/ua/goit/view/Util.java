package ua.goit.view;

import java.util.List;
import java.util.StringJoiner;

public class Util {
    public static <T> String joinListElements(List<T> tList) {
        StringJoiner joiner = new StringJoiner("<br>");
        for (T t : tList) {
            joiner.add(t.toString());
        }
        return joiner.toString();
    }
}
