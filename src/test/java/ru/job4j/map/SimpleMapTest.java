package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;

public class SimpleMapTest {

    SimpleMap<String, Integer> map;

    @Before
    public void initData() {
        map = new SimpleMap<>();
        map.put("word", 20);
        map.put("world", 14);
        map.put("vibe", 10);
    }

    @Test
    public void whenGet() {
        Assert.assertEquals(Integer.valueOf(10), map.get("vibe"));
        Assert.assertEquals(Integer.valueOf(14), map.get("world"));
    }

    @Test
    public void whenRemove() {
        Assert.assertTrue(map.remove("world"));
        Assert.assertEquals(2, map.size());
        Assert.assertFalse(map.remove("wrong"));
        map.put("water", 8);
        map.put("w", 5);
        Assert.assertTrue(map.remove("water"));
        Assert.assertEquals(3, map.size());
    }

    @Test
    public void whenRemoveByIncorrectKey() {
        Assert.assertFalse(map.remove("will"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveByIncorrectIndexThenGetException() {
        Iterator<String> iterator = map.iterator();
        map.put("key", 5);
        iterator.hasNext();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        Iterator<String> iterator = map.iterator();
        map.put("key", 5);
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenRemoveAfterGetIteratorThenMustBeException() {
        Iterator<String> iterator = map.iterator();
        map.remove("world");
        iterator.next();
    }

    @Test
    public void whenNoPlaceThenMustIncreaseCapacity() {
        HashMap<String, Integer> test = new HashMap();
        test.put("a", 1);
        test.put("b", 2);
        test.put("c", 3);
        test.forEach((k, v) -> map.put(k, v));
    }
}
