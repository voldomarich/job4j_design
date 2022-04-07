package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class ForwardLinkedTest {

    @Test
    public void whenDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(null);
        linked.add(10);
        linked.add(20);
        Iterator<Integer> it = linked.iterator();
        assertNull(it.next());
        assertThat(it.next(), is(10));
        assertThat(it.next(), is(20));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }
}
