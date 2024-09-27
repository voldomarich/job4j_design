package ru.job4j.ood.srp.store;

import ru.job4j.ood.srp.model.Employee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class MemStore implements Store {

    @XmlElement(name = "employee")
    private List<Employee> employees = new ArrayList<>();

    public MemStore() {
    }

    public MemStore(List<Employee> list) {
        this.employees = list;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public List<Employee> findBy(Predicate<Employee> filter) {
        return employees.stream().filter(filter).collect(Collectors.toList());
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
    }
}
