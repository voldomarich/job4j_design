package ru.job4j.collection;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    int countIn = 0;
    int countOut = 0;

    public T poll() {
        out.push(in.pop());
        countIn--;
        countOut++;
        return countIn == 0 ? out.pop() : null;
        }

    public void push(T value) {
        in.push(value);
    }
}
