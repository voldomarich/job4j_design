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
        date.set(2020, Calendar.NOVEMBER, 10, 23, 0);
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

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidSeat() {
        Cinema cinema = new Cinema3D();
        Account account = new AccountCinema();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 100, 20, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2000, 14, 33, 25, 0);
        Ticket ticket = cinema.buy(account, 10, 8, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBuyTicketSoldSeat() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.MAY, 3, 20, 0);
        Ticket ticket = cinema.buy(account, 10, 8, date);
        assertThat(ticket, is(new Ticket3D()));
        Ticket ticket2 = cinema.buy(account, 10, 8, date);
    }

    @Test
    public void whenSessionIsCancelled() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertTrue(sessions.contains(new Session3D()));
        sessions.remove(new Session3D());
        assertFalse(sessions.contains(new Session3D()));
    }
}
