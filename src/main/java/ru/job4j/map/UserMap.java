package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class UserMap {

    public static void main(String[] args) {

        HashMap<User, Object> map = new HashMap<>();

        User user1 = new User("Igor", 0, new GregorianCalendar(2000, Calendar.APRIL, 11));
        User user2 = new User("Igor", 0, new GregorianCalendar(2000, Calendar.APRIL, 11));

        map.put(user1, new Object());
        map.put(user2, new Object());

        for (User key : map.keySet()) {
            Object value = map.get(key);
            System.out.println(key + " " + value);
        }
    }
}
