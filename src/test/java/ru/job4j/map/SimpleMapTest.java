package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    SimpleMap<Integer, String> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
        map.put(1, "word");
        map.put(2, "world");
        map.put(3, "water");
    }

    @Test
    public void whenGet() {
        Assert.assertEquals("word", map.get(1));
        Assert.assertEquals("world", map.get(2));
    }

    @Test
    public void whenRemove() {
        Assert.assertTrue(map.remove(3));
        Assert.assertEquals(2, map.size());
        map.put(3, "will");
        map.put(4, "water");
        Assert.assertTrue(map.remove(4));
        Assert.assertEquals(3, map.size());
    }

    @Test
    public void whenRemoveByIncorrectKey() {
        Assert.assertFalse(map.remove(10));
    }

    @Test
    public void whenHasNext() {
        Iterator<Integer> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), iterator.next());
        Assert.assertEquals(Integer.valueOf(2), iterator.next());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
        Assert.assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNext() {
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenPutAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = map.iterator();
        map.put(4, "a");
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<Integer> iterator = map.iterator();
        map.remove(1);
        iterator.next();
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        map.put(4, "d");
        map.put(5, "e");
        map.put(6, "f");
        map.put(7, "g");
        map.put(8, "h");
        map.forEach((k) -> map.put(k, map.get(k)));
    }
}
