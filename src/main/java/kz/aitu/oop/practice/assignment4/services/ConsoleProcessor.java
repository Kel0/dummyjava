package kz.aitu.oop.practice.assignment4.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kz.aitu.oop.practice.assignment4.models.Employee;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.TypedQuery;

public class ConsoleProcessor {
    Session session;

    public ConsoleProcessor(Session session) {
        this.session = session;
    }

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));


        System.out.println("Hi! \nHow many users would you like to add?");
        String userCount = reader.readLine();
        System.out.println("UserName UserPosition(DEVELOPER, HR etc) UserSalary(example: 300000)");
        List<Employee> employeeList = new ArrayList<Employee>();

        if (userCount != null) {
            this.session.beginTransaction();
            for (int i = 0; i < Integer.parseInt(userCount); i++) {
                String user = reader.readLine();
                String[] userDetails = user.split(" ");
                Employee employee = new Employee(userDetails[0], userDetails[1], Integer.parseInt(userDetails[2]));
                session.save(employee);
                employeeList.add(employee);
            }

            CalculatorProcessor calc = new CalculatorProcessor(employeeList);
            System.out.println("Please, set time of completion in month");
            String month = reader.readLine();
            if (month != null) {
                System.out.println("Total price of project: " + calc.calculatePrice(Integer.parseInt(month)));
            }
            this.session.getTransaction().commit();
        }

        while (true) {
            System.out.println("1. Get users \n2. Add user \n3. Calculate price");
            String command = reader.readLine();

            if (command != null) {
                if (command.equals("1")) {
                    List query = this.session.createCriteria(Employee.class).list();
                    Iterator it = query.iterator();
                    System.out.print("Users list: \n");

                    while (it.hasNext()) {
                        Object o = (Object) it.next();
                        Employee empl = (Employee) o;
                        System.out.println(empl.getLogin() + " " + empl.getType() + " " + empl.getSalary());
                    }
                    System.out.print("\n");
                } else if (command.equals("2")) {
                    this.session.beginTransaction();
                    System.out.println("UserName UserPosition(DEVELOPER, HR etc) UserSalary(example: 300000)");
                    String user = reader.readLine();
                    String[] userDetails = user.split(" ");
                    Employee employee = new Employee(userDetails[0], userDetails[1], Integer.parseInt(userDetails[2]));
                    session.save(employee);
                    employeeList.add(employee);
                    this.session.getTransaction().commit();
                } else if (command.equals("3")) {
                    CalculatorProcessor calc = new CalculatorProcessor(employeeList);
                    System.out.println("Please, set time of completion in month");
                    String month = reader.readLine();
                    if (month != null) {
                        System.out.println("Total price of project: " + calc.calculatePrice(Integer.parseInt(month)));
                    }
                }
            }
        }
    }
}
