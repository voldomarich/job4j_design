package ru.job4j.collection.recursion;

public class Recursion {

    public int sum(int summary, int index) {
        if (index > 0) {
            summary += index;
            index--;
            summary = sum(summary, index);
        }
        return summary;
    }

    public int loop(int summary, int index) {
        for (int i = index; i > 0; i--) {
            summary += i;
        }
        return summary;
    }

    public long factorialLoop(int f) {
        long result = 1L;
        if (f > 0) {
            for (int i = 1; i <= f; i++) {
                result = result * i;
            }
        }
        return result;
    }

    public long factorialRecursion(long index) {
        return switch ((int) index) {
            case 0, 1 -> 1;
            default -> index * factorialRecursion(index - 1);
        };
    }

    public long fibonacciLoop(int n) {
        long result = 0L;
        if (n == 1) {
            result = 1L;
        } else if (n > 1) {
            long f1 = 1L;
            long f2 = 1L;
            for (int i = 0; i < (n - 2); i++) {
                result = f2 + f1;
                f1 = f2;
                f2 = result;
            }
        }
        return result;
    }

    public long fibonacciRecursion(int num) {
        return switch (num) {
            case 0 -> 0;
            case 1 -> 1;
            default -> fibonacciRecursion(num - 1) + fibonacciRecursion(num - 2);
        };
    }
}
