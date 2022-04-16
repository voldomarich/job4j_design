package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name)
                && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);  /* объявляем переменную result и
        инициализируем её хэш-кодом для первого поля объекта,
        вычислим хеш-код типа int для такого поля. */
        result = 31 * result + Integer.hashCode(children); /* Если поле примитивного типа,
        вычислим Type.hashCode(f), где
        Туре — упакованный примитивный класс, соответствующий типу f, 31 - нечетное простое число.
        Если бы оно было четным и умножение приводило к переполнению, то происходила бы потеря информации,
        потому что умножение на 2 эквивалентно сдвигу  */
        result = 31 * result + Objects.hash(birthday); /* Если поле представляет собой ссылку на объект,
        вычислим “каноническое представление” этого поля
        и вызовим для него hashCode. Если значение поля — null, будем использовать 0
        (или некоторую иную константу, но 0 — более традиционное значение)   */
        return result;
    }
}
