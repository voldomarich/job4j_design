package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        int countAdded = 0;
        int countChanged = 0;
        int countDeleted = 0;
        if (!previous.isEmpty() || !current.isEmpty()) {
            Map<Integer, String> map = previous.stream()
                    .collect(Collectors.toMap(User::getId, User::getName));
            Map<Integer, String> map2 = current.stream()
                    .collect(Collectors.toMap(User::getId, User::getName));
            for (Integer key : map.keySet()) {
                for (Integer key2 : map2.keySet()) {
                    if (!map2.containsKey(key)) {
                        countDeleted++;
                    } else if (!map.containsKey(key2)) {
                        countAdded++;
                    } else if (key.equals(key2) && !map.get(key).equals(map2.get(key2))) {
                        countChanged++;
                    }
                }
                return new Info(countAdded, countChanged, countDeleted);
            }
        }
        return null;
    }
}
