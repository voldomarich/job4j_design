package ru.job4j.generics;

public class UserStore {

    private final Store<User> store = new MemStore<>();

    @Override
    public void add(User model) {
        store.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        if (store.replace(id, model)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        if (store.delete(id)) {
            return true;
        }
        return false;
    }

    @Override
    public User findById(String id) {
        User user = new User();
        if (store.findById(id)) {
        return user;
    }
        return null;
}
}
