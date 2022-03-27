package ru.job4j.generics;

import java.util.HashMap;
import java.util.Map;

public class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    private int indexOf(int id, T model) {
        int result = -1;
        for (int i = 0; i < storage.size(); i++) {
            if (model.getId() = id) {
                result = i;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean replace(String id, T model) {
        int index = indexOf(id);
        boolean result = index != -1;
        if (result) {
            storage.entrySet(id);
            storage.put(model);
        }
        return result;

        @Override
        public boolean delete (String id){
            if (storage.remove(id) == id) {
                return true;
            }
            return false;
        }

        @Override
        public T findById (String id, T model){
            int index = indexOf(id);
            return index != -1 ? model : null;
        }
    }
}
