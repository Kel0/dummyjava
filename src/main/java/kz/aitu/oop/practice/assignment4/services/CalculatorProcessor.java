package kz.aitu.oop.practice.assignment4.services;

import kz.aitu.oop.practice.assignment4.models.Employee;

import java.util.List;

public class CalculatorProcessor {
    public List<Employee> employees;

    public CalculatorProcessor(List<Employee> employees) {
        this.employees = employees;
    }

    public int calculatePrice(int month) {
        int price = 0;
        for (Employee employee : this.employees) {
            price += employee.getSalary();
        }
        return price * month;
    }
}
