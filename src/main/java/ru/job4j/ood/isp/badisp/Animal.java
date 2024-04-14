package ru.job4j.ood.isp.badisp;

/* У собаки нет опции летать и ползать. У змеи - летать и бегать. У птицы - ползать.
Но им всем приходится поддерживать интерфейс Animal.
Решение - разделить интерфейсы, и реализовывать потом каждому животному только нужные ему интерфейсы */

public interface Animal {

    void eat();
    void sleep();
    void fly();
    void crawl();
    void run();
}

class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("Dig in!");
    }

    @Override
    public void sleep() {
        System.out.println("Sweet dreams!");
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void crawl() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void run() {
        System.out.println("Run, Rex, run!");
    }
}

class Bird implements Animal {

    @Override
    public void eat() {
        System.out.println("Dig in!");
    }

    @Override
    public void sleep() {
        System.out.println("Sweet dreams!");
    }

    @Override
    public void fly() {
        System.out.println("I am a birdie and able to fly");
    }

    @Override
    public void crawl() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void run() {
        System.out.println("I can run as well as fly");
        }
}

class Snake implements Animal {

    @Override
    public void eat() {
        System.out.println("Dig in!");
    }

    @Override
    public void sleep() {
        System.out.println("Sweet dreams!");
    }

    @Override
    public void fly() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void crawl() {
        System.out.println("Would you mind crawling there");
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException();
    }
}
