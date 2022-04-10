package ru.job4j.collection;

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    int countIn = 0;
    int countOut = 0;

    public T poll() {
        if (countOut == 0) {
            while (countIn != 0) {
                out.push(in.pop());
                countIn--;
                countOut++;
            }
                return out.pop();
            }
        return null;
    }

    public void push(T value) {
        in.push(value);
        countIn++;
    }
}
