package ru.job4j.io;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ArgsNameTest {

    @Test
    public void whenGetFirst() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        assertThat(jvm.get("Xmx"), is("512"));
        assertThat(jvm.get("encoding"), is("UTF-8"));
    }

    @Test
    public void whenGetFirstReorder() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenArgHasEqualElements() {
        ArgsName jvm = ArgsName.of(new String[] {"-encoding=UTF-8", "-Xmx=512", "-Xmx=256"});
        assertThat(jvm.get("Xmx"), is("256"));
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test
    public void whenMultipleEqualsSymbol() {
        ArgsName jvm = ArgsName.of(new String[] {"-request=?msg=Exit="});
        assertThat(jvm.get("request"), is("?msg=Exit="));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenGetNotExist() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        jvm.get("Xms");
    }

    @Test
    public void whenGetHasOneArg() {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512"});
        assertThat(jvm.get("Xmx"), is("512"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenArgStartsWithWrongSigns() {
        ArgsName jvm = ArgsName.of(new String[] {"-=UTF-8", "-Xmx=512"});
        jvm.get("Xmx");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongSomeArgument() {
        ArgsName jvm = ArgsName.of(new String[] {"-enconding=UTF-8", "-Xmx="});
        jvm.get("Xmx");
    }
}
