package ru.job4j.additional;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class PutContainsKeyTest {

    @Test
    public void addNewElement() {
        PutContainsKey.User one = new PutContainsKey.User(1, "Name1");
        PutContainsKey.User two = new PutContainsKey.User(2, "Name2");
        PutContainsKey.User three = new PutContainsKey.User(1, "Name3");
        List<PutContainsKey.User> list = List.of(one, two, three);
        Map<Integer, PutContainsKey.User> map = PutContainsKey.addNewElementWithCheck(list);
        Map<Integer, PutContainsKey.User> expected = Map.of(1, one, 2, two);
        assertThat(map, is(expected));
    }

    @Test
    public void addNewElementWithoutCheck() {
        PutContainsKey.User one = new PutContainsKey.User(1, "Name1");
        PutContainsKey.User two = new PutContainsKey.User(2, "Name2");
        PutContainsKey.User three = new PutContainsKey.User(1, "Name3");
        List<PutContainsKey.User> list = List.of(one, two, three);
        Map<Integer, PutContainsKey.User> map = PutContainsKey.addNewElementWithoutCheck(list);
        Map<Integer, PutContainsKey.User> expected = Map.of(1, three, 2, two);
        assertThat(map, is(expected));
    }
}
