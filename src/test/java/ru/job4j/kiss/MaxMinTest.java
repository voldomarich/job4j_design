package ru.job4j.kiss;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest {

    @Test
    public void whenMin() {
        List<Integer> value = List.of(0, 5, 10);
        Comparator<Integer> comparator = Integer::compare;
        int result = MaxMin.min(value, comparator);
        int expected = 0;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void whenMax() {
        List<Integer> value = List.of(0, 5, 10);
        Comparator<Integer> comparator = Integer::compare;
        int result = MaxMin.max(value, comparator);
        int expected = 10;
        Assert.assertEquals(expected, result);
    }

    @Test
    public void whenEqual() {
        List<Integer> value = List.of(10, 10, 10);
        Comparator<Integer> comparator = Integer::compare;
        int result = MaxMin.min(value, comparator);
        int expected = 10;
        Assert.assertEquals(expected, result);
        result = MaxMin.max(value, comparator);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void whenStringIsMin() {
        List<String> value = new ArrayList<>();
        value.add("a");
        value.add("an");
        value.add("the");
        Comparator<String> comparator = String::compareTo;
        String result = MaxMin.min(value, comparator);
        String expected = "a";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void whenStringIsMax() {
        List<String> value = List.of("a", "an", "the");
        Comparator<String> comparator = String::compareTo;
        String result = MaxMin.max(value, comparator);
        String expected = "the";
        Assert.assertEquals(expected, result);
    }
}
