package ru.job4j.newcoll.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class TreeUtilsTest {

    private TreeUtils<Integer> treeUtils;

    private Node<Integer> tree = new Node<>(1,
            new Node<>(2,
                    new Node<>(4,
                            new Node<>(8, new Node<>(16), new Node<>(17)),
                            new Node<>(9, new Node<>(18), new Node<>(19))),
                    new Node<>(5,
                            new Node<>(10, new Node<>(20), new Node<>(21)),
                            new Node<>(11, new Node<>(22), new Node<>(23))
                    )
            ),
            new Node<>(3,
                    new Node<>(6,
                            new Node<>(12, new Node<>(24), new Node<>(25)),
                            new Node<>(13, new Node<>(26), new Node<>(27))),
                    new Node<>(7,
                            new Node<>(14, new Node<>(28), new Node<>(29)),
                            new Node<>(15, new Node<>(30), new Node<>(31)))
            )
    );

    @BeforeEach
    void init() {
        treeUtils = new TreeUtils<>();
    }

    @Test
    void checkCount() {
        assertThat(treeUtils.countNode(tree)).isEqualTo(31);
        Node<Integer> node = new Node<>(10, new Node<>(20));
        assertThat(treeUtils.countNode(node)).isEqualTo(2);
        node.addChildren(new Node<>(21));
        assertThat(treeUtils.countNode(node)).isEqualTo(3);
        assertThatThrownBy(() -> treeUtils.countNode(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkFindAll() {
        assertThat(treeUtils.findAll(tree)).containsExactlyInAnyOrder(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
                17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
        assertThat(treeUtils.findAll(new Node<>(10))).containsExactly(10);
        assertThat(treeUtils.findAll(new Node<>(32))).doesNotContain(10);
        assertThatThrownBy(() -> treeUtils.findAll(null)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkAdd() {
        Node<Integer> root = new Node<>(1);
        assertThat(treeUtils.add(root, 1, 2)).isTrue();
        assertThat(treeUtils.findAll(root)).containsExactly(1, 2);
        assertThat(treeUtils.countNode(root)).isEqualTo(2);
        assertThat(treeUtils.add(root, 3, 1)).isFalse();
        assertThat(treeUtils.findAll(root)).containsExactly(1, 2);
        assertThat(treeUtils.countNode(root)).isEqualTo(2);
        assertThatThrownBy(() -> treeUtils.add(null, 1, 2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkFindByKey() {
        Optional<Node<Integer>> result = treeUtils.findByKey(tree, 2);
        assertThat(result).isPresent();
        assertThat(treeUtils.countNode(result.get())).isEqualTo(15);
        assertThat(treeUtils.findAll(result.get())).containsExactlyInAnyOrder(
                2, 5, 11, 23, 22, 10, 21, 20, 4, 9, 19, 18, 8, 17, 16);
        assertThat(treeUtils.countNode(tree)).isEqualTo(31);
        assertThatThrownBy(() -> treeUtils.findByKey(null, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkDivideTreeBy2() {
        Optional<Node<Integer>> result = treeUtils.divideByKey(tree, 2);
        assertThat(result).isPresent();
        assertThat(treeUtils.countNode(result.get())).isEqualTo(15);
        assertThat(treeUtils.findAll(result.get())).containsExactlyInAnyOrder(
                2, 5, 11, 23, 22, 10, 21, 20, 4, 9, 19, 18, 8, 17, 16);
        assertThat(treeUtils.countNode(tree)).isEqualTo(16);
        assertThat(treeUtils.findAll(tree)).doesNotContain(
                2, 5, 11, 23, 22, 10, 21, 20, 4, 9, 19, 18, 8, 17, 16);
        assertThatThrownBy(() -> treeUtils.divideByKey(null, 1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void checkDivideTreeBy3() {
        Optional<Node<Integer>> result = treeUtils.divideByKey(tree, 3);
        assertThat(result).isPresent();
        assertThat(treeUtils.countNode(result.get())).isEqualTo(15);
        assertThat(treeUtils.findAll(result.get())).containsExactlyInAnyOrder(
                3, 6, 12, 13, 24, 25, 26, 27, 7, 14, 15, 28, 29, 30, 31);
        assertThat(treeUtils.countNode(tree)).isEqualTo(16);
        assertThat(treeUtils.findAll(tree)).containsExactlyInAnyOrder(
                1, 2, 5, 11, 23, 22, 10, 21, 20, 4, 9, 19, 18, 8, 17, 16);
        assertThat(treeUtils.findAll(tree)).doesNotContain(
                3, 6, 12, 13, 24, 25, 26, 27, 7, 14, 15, 28, 29, 30, 31);
    }

    @Test
    void checkDivideTreeBy4() {
        Optional<Node<Integer>> result = treeUtils.divideByKey(tree, 4);
        assertThat(result).isPresent();
        assertThat(treeUtils.countNode(result.get())).isEqualTo(7);
        assertThat(treeUtils.findAll(result.get())).containsExactlyInAnyOrder(
                4, 8, 9, 16, 17, 18, 19);
        assertThat(treeUtils.countNode(tree)).isEqualTo(24);
        assertThat(treeUtils.findAll(tree)).containsExactlyInAnyOrder(
                1, 3, 6, 12, 13, 24, 25, 26, 27, 7, 14, 15, 28, 29, 30, 31, 2, 5, 10, 11, 20, 21, 22, 23);
    }

    @Test
    void checkDivideTreeBy6() {
        Optional<Node<Integer>> result = treeUtils.divideByKey(tree, 6);
        assertThat(result).isPresent();
        assertThat(treeUtils.countNode(result.get())).isEqualTo(7);
        assertThat(treeUtils.findAll(result.get())).containsExactlyInAnyOrder(
                6, 12, 13, 24, 25, 26, 27);
        assertThat(treeUtils.countNode(tree)).isEqualTo(24);
        assertThat(treeUtils.findAll(tree)).containsExactlyInAnyOrder(
                1, 2, 4, 8, 9, 16, 17, 18, 19, 5, 10, 11, 20, 21, 22, 23, 3, 7, 14, 15, 28, 29, 30, 31);
    }

    @Test
    void checkDivideTreeBy8() {
        Optional<Node<Integer>> result = treeUtils.divideByKey(tree, 8);
        assertThat(result).isPresent();
        assertThat(treeUtils.countNode(result.get())).isEqualTo(3);
        assertThat(treeUtils.findAll(result.get())).containsExactlyInAnyOrder(
                8, 16, 17);
        assertThat(treeUtils.countNode(tree)).isEqualTo(28);
        assertThat(treeUtils.findAll(tree)).containsExactlyInAnyOrder(
                1, 3, 6, 12, 13, 24, 25, 26, 27, 7, 14, 15, 28, 29, 30, 31, 2, 4, 9, 18, 19, 5, 10, 11, 20, 21, 22, 23);
    }

    @Test
    void checkDivideTreeByRoot() {
        Optional<Node<Integer>> result = treeUtils.divideByKey(tree, 1);
        assertThat(result).isPresent();
        assertThat(treeUtils.countNode(result.get())).isEqualTo(31);
    }
}
