package ru.job4j.collection;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    @Test
    public void whenPush() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(10);
        stack.push(80);
        stack.push(1000);
        assertThat(stack.pop(), is(1000));
    }

    @Test
    public void whenPushThenPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        assertThat(stack.pop(), is(1));
    }

    @Test
    public void whenPushPollThenPushPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertThat(stack.pop(), is(2));
    }

    @Test
    public void whenPushPushThenPollPoll() {
        SimpleStack<Integer> stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertThat(stack.pop(), is(1));
    }
}
