
package ru.job4j.map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleMapTest {

    SimpleMap<String, int> map;

    @Before
    public void initData() {
        SimpleMap<String, int> map = new SimpleMap<String, int>();
        map.put("word", 20);
        map.put("world", 14);
        map.put("vibe", 10);
    }

    @Test
    public void whenGet() {
        Assert.assertEquals(10, map.get("vibe"));
        Assert.assertEquals(20, map.get("world"));
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

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        Assert.assertEquals(1, map.iterator().next());
        Assert.assertEquals(1, map.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(1, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(2, iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(3, iterator.next());
        Assert.assertFalse(iterator.hasNext());
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
