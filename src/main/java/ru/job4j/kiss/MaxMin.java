package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class MaxMin {

    static <T> T find(List<T> value, Predicate<T> predicate) {
        T unit = value.get(0);
        for (T t : value) {
            if (predicate.test(t)) {
                unit = t;
            }
        }
        return unit;
    }

    static <T> T min(List<T> value, Comparator<T> comparator) {
        Predicate<T> predicate = comparator::equals;
        return find(value, predicate);
    }

    static <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator::equals);
    }
}
