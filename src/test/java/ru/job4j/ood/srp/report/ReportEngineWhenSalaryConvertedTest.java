package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.InMemoryCurrencyConverter;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

class ReportEngineWhenSalaryConvertedTest {

    @Test
    public void whenSalaryConverted() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 10000);
        Employee worker2 = new Employee("Ilia", now, now, 250000);
        Employee worker3 = new Employee("Ildus", now, now, 14000);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        store.add(worker2);
        store.add(worker3);
        Report engine = new ReportEngineWhenSalaryConverted(store, parser);
        double salary;
        for (Employee employee : store.findAll()) {
            salary = new InMemoryCurrencyConverter().convert(Currency.RUB, employee.getSalary(), Currency.USD);
            StringBuilder expect = new StringBuilder()
                    .append("Name; Hired; Fired; SalaryRub; SalaryUsd;")
                    .append(System.lineSeparator())
                    .append(employee.getName()).append(" ")
                    .append(parser.parse(employee.getHired())).append(" ")
                    .append(parser.parse(employee.getFired())).append(" ")
                    .append(employee.getSalary()).append(" ")
                    .append(salary)
                    .append(System.lineSeparator());
            assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
        }
    }
}
