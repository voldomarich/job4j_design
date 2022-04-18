package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

public class MaxMinTest {

    public class MinTest {
        @Test
        public void whenMin() {
            List<Integer> value = List.of(0, 5, 10);
            Comparator<Integer> comparator = f -> Integer.compare(value.get(0), f);
            int result = MaxMin.min(value, comparator);
            int expected = 0;
            Assert.assertEquals(expected, result);
        }

        @Test
        public void whenMax() {
            List<Integer> value = List.of(0, 5, 10);
            Comparator<Integer> comparator = f -> Integer.compare(f, value.get(0));
            int result = MaxMin.max(value, comparator);
            int expected = 10;
            Assert.assertEquals(expected, result);
        }

        @Test
        public void whenEqual() {
            List<Integer> value = List.of(10, 10, 10);
            Comparator<Integer> comparator = f -> Integer.compare(value.get(0), f);
            int result = MaxMin.min(value, comparator);
            int expected = 10;
            Assert.assertEquals(expected, result);
        }
    }
}
