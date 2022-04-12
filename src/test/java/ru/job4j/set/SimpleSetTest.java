package ru.job4j.set;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertTrue(set.add(4));
    }

    @Test
    public void whenAdd() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(0));
        assertTrue(set.contains(0));
        assertTrue(set.add(1));
        assertTrue(set.add(null));
        assertFalse(set.contains(2));
        assertFalse(set.add(null));
    }

    @Test
    public void whenUseIterator() {
        Set<Integer> set = new SimpleSet<>();
        set.add(null);
        set.add(0);
        assertTrue(set.add(1));
        assertThat(set.iterator(), is(null));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenUseAnotherIterator() {
        Set<Integer> set = new SimpleSet<>();
        set.iterator();
    }
}
