package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;


class ReportEngineTestCSV {

    @Test
    public void whenFormatCSV() {
        MemStore store = new MemStore();
        Calendar dateHired = Calendar.getInstance();
        dateHired.set(2019, Calendar.FEBRUARY, 26);
        Calendar dateFired = Calendar.getInstance();
        dateFired.set(2022, Calendar.FEBRUARY, 28);
        Employee worker = new Employee("Ivan", dateHired, dateFired, 45000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportEngineCSV(store, parser);
        String expect = String.join(
                "Name; Hired; Fired; Salary;",
                System.lineSeparator(),
                worker.getName(),
                parser.parse(worker.getHired()),
                parser.parse(worker.getFired()),
                System.lineSeparator());
        assertThat(engine.generate(em -> true)).isEqualTo(expect);
    }
}
