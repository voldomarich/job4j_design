package ru.job4j.ood.ocp.badocp;

import java.util.List;

/* здесь лучше создать новые сущности private static class Dog и т.п.,
унаследовать от Animal, в методе main добавить их в список
 */
public class AnimalInheritance {

    private static class Animal {
        public String utterCow() {
            return "moo";
        }

        public String utterDog() {
            return "bark";
        }
    }

    public static void main(String[] args) {
        List<Animal> animals = List.of(new Animal(), new Animal());
        System.out.println(animals.get(0).utterCow());
        System.out.println(animals.get(1).utterDog());
    }
}
