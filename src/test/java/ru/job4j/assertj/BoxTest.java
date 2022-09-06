package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisSphere1() {
        Box box = new Box(5, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void isThisSphere2() {
        Box box = new Box(8, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void getNumberOfVertices() {
        Box box = new Box(4, 10);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(4);
        assertThat(box.whatsThis()).isEqualTo("Tetrahedron");
    }

    @Test
    void getNumberOfVertices2() {
        Box box = new Box(2, 10);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(-1);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void isExist() {
        Box box = new Box(8, 10);
        boolean status = box.isExist();
        assertThat(status).isEqualTo(true);
    }

    @Test
    void isExist2() {
        Box box = new Box(11, 10);
        boolean status = box.isExist();
        assertThat(status).isEqualTo(false);
    }

    @Test
    void isExist3() {
        Box box = new Box(4, 0);
        boolean status = box.isExist();
        assertThat(status).isEqualTo(false);
    }

    @Test
    void getArea() {
        Box box = new Box(8, 0);
        double result = box.getArea();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void getArea2() {
        Box box = new Box(0, 11);
        double result = box.getArea();
        assertThat(result).isEqualTo(484 * Math.PI);
    }

    @Test
    void getArea3() {
        Box box = new Box(4, 10);
        double result = box.getArea();
        assertThat(result).isEqualTo(100 * Math.sqrt(3));
    }

    @Test
    void getArea4() {
        Box box = new Box(8, 2);
        double result = box.getArea();
        assertThat(result).isEqualTo(24);
    }
}
