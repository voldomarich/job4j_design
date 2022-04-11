package ru.job4j.map;

import java.util.HashMap;

public class UserMap {

    HashMap<User, Object> map = new HashMap<>();

    User user1 = new User(Igor, 0, 11.11);
    User user2 = new User(Igor, 2, 12.12);

    map.put(user1, new Object());
    map.put(user2, new Object());

    for (Object key : map.keySet()) {
        Object value = map.get(key);
        System.out.println(key + " " + value);
    }
}
