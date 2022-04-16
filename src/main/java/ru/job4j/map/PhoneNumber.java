package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class PhoneNumber {
    public static void main(String[] args) {
        Map<User, String> m = new HashMap<>();
        m.put(new User("Jenny", 21,
                new GregorianCalendar(2000, Calendar.APRIL, 16)), "Raynolds");
    }
}
