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
            for (int i = 0; i < map.size(); i++) {
                if (map.containsKey(i) && !map2.containsKey(i)) {
                    countDeleted++;
                } else if (!map.containsKey(i) && map2.containsKey(i)) {
                    countAdded++;
                } else if (!map.get(i).equals(map2.get(i))) {
                    countChanged++;
                }
                return new Info(countAdded, countChanged, countDeleted);
            }
        }
        return null;
    }
}
