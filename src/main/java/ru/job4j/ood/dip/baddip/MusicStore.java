package ru.job4j.ood.dip.baddip;

import ru.job4j.ood.srp.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* У сервиса есть единственное поле - список для хранения данных.
С точки зрения DIP, это нарушение, потому что мы зависим от реализации, а не абстракции.
Решение - выделение абстракции /интерфейса/ для хранилища и уже далее от него нужно будет реализовать
InMemoryMusicStore и в самом сервисе избавиться от зависимости на хранилище.
Тогда можно будет его подменить на любое другое, т.к. нет прямой зависимости
*/

public class MusicStore {
    private final List<Employee> employees = new ArrayList<>();

    public void add(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}
