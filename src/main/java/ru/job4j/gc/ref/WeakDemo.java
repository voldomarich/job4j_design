package ru.job4j.gc.ref;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WeakDemo {

    public static void main(String[] args) throws InterruptedException {
        //example1Incorrect();
        example1Correct();
        //example2Incorrect();
        //example2Correct();
    }

    // За'null'ение сильной ссылки приводит к удалению объекта и мы его не можем получить по слабой ссылке
    private static void example1Incorrect() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        object = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    // Получаем из слабой ссылки сильную ссылку на данные,
    // после чего за'null'яем её и GC не удаляет объект,
    // мы можем получить его по слабой ссылке
    private static void example1Correct() throws InterruptedException {
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        Object strong = weak.get();
        strong = null;
        System.gc();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(weak.get());
    }

    // Cоздаём объекты изначально без сильных ссылок. При вызове сборщика мусора все объекты удаляются
    private static void example2Incorrect() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            objects.add(new WeakReference<Object>(new Object() {
                @Override
                protected void finalize() throws Throwable {
                    System.out.println("Removed!");
                }
            }));
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }

    // Сoздаём объект с сильной ссылкой, создаём слабую ссылку на него. После чего добавляем в коллекцию
    // слабую ссылку на объект, и GC не затрёт наши объекты
    private static void example2Correct() throws InterruptedException {
        List<WeakReference<Object>> objects = new ArrayList<>();
        Object object = new Object() {
            @Override
            protected void finalize() throws Throwable {
                System.out.println("Removed!");
            }
        };
        WeakReference<Object> weak = new WeakReference<>(object);
        for (int i = 0; i < 100; i++) {
            objects.add(weak);
        }
        System.gc();
        TimeUnit.SECONDS.sleep(3);
    }
}
