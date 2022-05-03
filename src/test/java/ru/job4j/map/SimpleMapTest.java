package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

public class SimpleMapTest {

    SimpleMap<String, Integer> map;

    @Before
    public void initData() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("word", 20);
        map.put("world", 14);
        map.put("vibe", 10);
    }

    @Test
    public void whenGet() {
        Assert.assertEquals(Integer.valueOf(10), map.get("vibe"));
        Assert.assertEquals(Integer.valueOf(20), map.get("world"));
    }

    @Test
    public void whenRemove() {
        Assert.assertTrue(map.remove("world"));
        Assert.assertEquals(2, map.size());
        Assert.assertFalse(map.remove("wrong"));
        map.put("water", 8);
        map.put("w", 10);
        Assert.assertTrue(map.remove("water"));
        Assert.assertEquals(3, map.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveByIncorrectIndexThenGetException() {
        map.remove("will");
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        IntStream.range(3, 10).forEach(map::put);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<String> iterator = map.iterator();
        map.put("key", 10);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<String> iterator = map.iterator();
        map.remove("world");
        iterator.next();
    }
}
