package ru.job4j.tdd;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void whenBuyTicketEqualSeat() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, 05, 03, 20, 00);
        Ticket ticket = cinema.buy(account, 10, 8, date);
        assertThat(ticket, is(new Ticket3D()));
        Ticket ticket2 = cinema.buy(account, 10, 8, date);
        assertNull(ticket2);
    }

    @Test
    public void whenSessionOfCancel() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        sessions.remove(new Session3D());
        assertFalse(sessions.contains(new Session3D()));
    }
}
