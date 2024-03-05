package ru.job4j.ood.srp.badsrp;

import java.util.Date;

/* В модель данных не нужно вносить методы, работающие с этой моделью данных (здесь это - add, findById, delete).
Класс будет изменяться, если нам нужно будет как-то изменить сущность «студент», например, добавить новое свойство,
обозначающее год рождения.
Также класс будет изменяться, когда нам будет необходимо изменить логику работы с базой данных, например,
добавить метод, который выбирает всех студентов моложе двадцати лет.
* Данные методы необходимо вынести в отдельный класс  */
public class Student {

    private String name;
    private String group;
    private Date created;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public Student add(Student student) {
        return student;
    }

    public Student findById(int id) {
        return null;
    }

    public Student delete(Student student) {
        return student;
    }
}
