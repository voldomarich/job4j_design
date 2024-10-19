package ru.job4j.collection.recursion;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class RecursionTest {

    @Test
    void checkSummary() {
        Recursion recursion = new Recursion();
        boolean result = recursion.loop(1, 5) == recursion.sum(1, 5);
        assertThat(result).isTrue();
        assertThat(recursion.loop(1, 5)).isEqualTo(16);
        assertThat(recursion.sum(1, 5)).isEqualTo(16);
        assertThat(recursion.sum(11, 6)).isEqualTo(32);
    }

    @Test
    void checkFactorial() {
        Recursion recursion = new Recursion();
        boolean result = recursion.factorialLoop(0) == recursion.factorialRecursion(0);
        assertThat(result).isTrue();
        result = recursion.factorialLoop(1) == recursion.factorialRecursion(1);
        assertThat(result).isTrue();
        result = recursion.factorialLoop(5) == recursion.factorialRecursion(5);
        assertThat(result).isTrue();
        assertThat(recursion.factorialRecursion(1)).isEqualTo(1);
        assertThat(recursion.factorialRecursion(5)).isEqualTo(120);
    }

    @Test
    void checkFibonacci() {
        Recursion recursion = new Recursion();
        boolean result = recursion.fibonacciLoop(0) == recursion.fibonacciRecursion(0);
        assertThat(result).isTrue();
        result = recursion.fibonacciLoop(1) == recursion.fibonacciRecursion(1);
        assertThat(result).isTrue();
        result = recursion.fibonacciLoop(5) == recursion.fibonacciRecursion(5);
        assertThat(result).isTrue();
        assertThat(recursion.fibonacciRecursion(3)).isEqualTo(2);
        assertThat(recursion.fibonacciRecursion(6)).isEqualTo(8);
        assertThat(recursion.fibonacciRecursion(8)).isEqualTo(21);
    }
}
