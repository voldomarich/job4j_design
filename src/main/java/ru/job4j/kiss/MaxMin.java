package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class MaxMin {

    static <T> T find(Predicate<T> predicate) {
        T unit = Objects.get(0);
        for (T t : value) {
            if (predicate.test(t)) {
                unit = value.get(t);
            }
        }
        return unit;
    }

    static  <T> T min(List<T> value, Comparator<T> comparator) {
        return find(T -> comparator.equals(value));
    }

    static  <T> T max(List<T> value, Comparator<T> comparator) {
        return find(T -> value.equals(comparator));
    }
}
