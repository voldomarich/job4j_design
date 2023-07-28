package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineWhenReversedTest {

    @Test
    public void whenReversed() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Ilia", now, now, 250);
        Employee worker3 = new Employee("Ildus", now, now, 1000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportEngine(store, parser);
        String expect = "Name; Salary;" +
                System.lineSeparator() +
                worker.getName() + " " +
                worker.getSalary() +
                worker2.getName() + " " +
                worker2.getSalary() +
                worker3.getName() + " " +
                worker3.getSalary() +
                System.lineSeparator();
        assertThat(engine.generate(em -> em.getSalary() < 0))
                .isEqualTo(expect);
    }
}
