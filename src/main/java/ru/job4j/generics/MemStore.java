package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.put("key", model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (!storage.replace(id, model)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        if (storage.delete(id)) {
            return false;
        }
        return true;
    }

    @Override
    public T findById(String id) {
        storage.find(id);
        return T;
    }
}
