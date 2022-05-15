package ru.job4j.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {

        int countAdded = 0;
        int countChanged = 0;
        int countDeleted = 0;

        if (!previous.isEmpty() && !current.isEmpty()) {
            Map<Integer, String> map = previous.stream()
                    .collect(Collectors.toMap(User::getId, User::getName));
            Map<Integer, String> map2 = current.stream()
                    .collect(Collectors.toMap(User::getId, User::getName));
            for (Integer key : map.keySet()) {
                if (!map2.containsKey(key)) {
                    countDeleted++;
                } else if (!map.get(key).equals(map2.get(key))) {
                    countChanged++;
                }
            }
            countAdded = countDeleted + current.size() - previous.size();
            return new Info(countAdded, countChanged, countDeleted);
        }
        return null;
    }
}
