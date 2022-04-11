package ru.job4j.tree;

import org.junit.Test;

import static org.junit.Assert.*;
import  static org.hamcrest.Matchers.is;

public final class SimpleTreeTest {

    @Test
    public void whenContainsNull() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, null);
        assertTrue(tree.findBy(null).isPresent());
        assertFalse(tree.add(2, 0));
        assertTrue(tree.add(1, 0));
    }

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenChildExistOnLeafThenNotAdd() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.add(2, 6));
    }
}