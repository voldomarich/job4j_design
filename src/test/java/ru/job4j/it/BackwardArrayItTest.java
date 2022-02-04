package ru.job4j.it;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class BackwardArrayItTest {

    @Test
    public void whenMultiCallhasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {8, 7, 1}
        );
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(7));
        assertThat(it.next(), is(8));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        it.next();
    }
}
