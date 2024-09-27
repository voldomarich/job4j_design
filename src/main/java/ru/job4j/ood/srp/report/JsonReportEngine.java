package ru.job4j.ood.srp.report;

import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.formatter.JsonDateTimeAdapter;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private final Store store;

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Calendar.class, new JsonDateTimeAdapter())
                .registerTypeAdapter(GregorianCalendar.class, new JsonDateTimeAdapter())
                .create()
                .toJson(store.findBy(filter));
    }
}
