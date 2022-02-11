package kz.aitu.oop.practice.assignment4.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    public Employee() {};
    public Employee(String login, String type, Integer salary) {
        this.login = login;
        this.type = type;
        this.salary = salary;
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String type;
    private Integer salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}