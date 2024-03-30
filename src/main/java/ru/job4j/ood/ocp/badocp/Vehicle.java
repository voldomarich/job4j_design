package ru.job4j.ood.ocp.badocp;

import java.util.List;

/* если мы хотим добавить Airbus, Ship, Spaceship, то использовать наследование
не можем, так как при наследовании наследуется состояние объекта, а самолёт - это не автобус,
наследование можно использовать только при устойчивой иерархии классов,
здесь лучше использовать интерфейсы, они дают гибкость
 */
public class Vehicle {

    private static class Bus {
        public String move() {
            return "ride";
        }
    }

    public static void main(String[] args) {
        List<Bus> rectangles = List.of(new Bus());
        rectangles.forEach(Bus::move);
    }
}
