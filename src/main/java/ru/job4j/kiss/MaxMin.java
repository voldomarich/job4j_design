package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {

    static <T> T find(List<T> value, BiPredicate<T, T> predicate) {
        T unit = value.get(0);
        for (T t : value) {
            if (predicate.test(t, unit)) {
                unit = t;
            }
        }
        return unit;
    }

    static <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, (a, b) -> comparator.compare(a, b) < 0);
    }

    static <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, (a, b) -> comparator.compare(a, b) > 0);
    }
}
