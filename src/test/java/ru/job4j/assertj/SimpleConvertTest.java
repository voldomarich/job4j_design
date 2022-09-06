package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("zero", "one", "two", "three", "four");
        assertThat(list).first().isEqualTo("zero");
        assertThat(list).element(3).isNotNull().isEqualTo("three");
        assertThat(list).last().isNotNull().isEqualTo("four");
        assertThat(list).filteredOn(e -> e.startsWith("t")).last().isNotNull().isEqualTo("three");
        assertThat(list).filteredOnAssertions(e -> assertThat(e.length()).isGreaterThan(4))
                .hasSize(1)
                .first()
                .isEqualTo("three");
        assertThat(list).anySatisfy(e -> {
            assertThat(e.length()).isLessThan(5);
            assertThat(e.length()).isGreaterThan(1);
        });
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("he", "would", "seem", "to", "have",
                "to", "come", "over", "to", "us");
        assertThat(set).isNotNull().hasSize(8)
                .contains("to")
                .containsAnyOf("you", "our", "over")
                .allMatch(e -> e.length() >= 2)
                .anyMatch(e -> e.length() == 4)
                .noneMatch(e -> e.length() > 5);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("he", "she", "we", "they");
        assertThat(map).hasSize(4)
                .containsKeys("he", "she", "we", "they")
                .containsValues(0, 1, 2, 3)
                .doesNotContainKey("you")
                .doesNotContainValue(10)
                .containsEntry("he", 0)
                .containsEntry("we", 2)
                .containsEntry("they", 3);
    }
}
