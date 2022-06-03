package ru.job4j.template;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenUniqueKeysThenUseThem() {
        Compiler compiler = new Compiler();
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vladimir");
        map.put("subject", "you");
        String expected = "I am Vladimir, Who are you?";
        String result = compiler.produce(template, map);
        Assert.assertThat(result, is(expected));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenPatternHasKeyMapHasNot() {
        Compiler compiler = new Compiler();
        String template = "I am ${name}, Who are ${subject}, Who is ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vladimir");
        map.put("subject", "you");
        compiler.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapHasKeyPatternHasNot() {
        Compiler compiler = new Compiler();
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        map.put("name", "Vladimir");
        map.put("subject", "you");
        map.put("name1", "Igor");
        compiler.produce(template, map);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenMapIsEmpty() {
        Compiler compiler = new Compiler();
        String template = "I am ${name}, Who are ${subject}?";
        Map<String, String> map = new HashMap<>();
        compiler.produce(template, map);
    }
}
