package ru.job4j.template;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void whenGetFirst() {
        Compiler compiler = new Compiler();
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThat("I am a Vladimir, Who are you? ", compiler.produce(template, "Vladimir", "you"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMapHasThirdArg() {
        Compiler compiler = new Compiler();
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThat("I am a doctor, Who are you? ",
                compiler.produce(template, "doctor", "you", "they"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPatternHasKeyMapHasNot() {
        Compiler compiler = new Compiler();
        String template = "I am a ${name}, Who are ${subject}? ";
        assertThat("I am a doctor, Who are you? ",
                compiler.produce(template, "doctor", "you");
    }
}
