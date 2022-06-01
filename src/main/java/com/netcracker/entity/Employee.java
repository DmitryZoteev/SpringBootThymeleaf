package com.netcracker.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Поле Surname не может быть пустым")
    @Size(min = 2, max = 30, message = "Surname не может быть короче 2-х и длиньше 30 символов")
    private String surname;
    @NotEmpty(message = "Поле Name не может быть пустым")
    @Size(min = 2, max = 30, message = "Name не может быть короче 2-х и длиньше 30 символов")
    private String name;
    @NotEmpty(message = "Поле Patronymic не может быть пустым")
    @Size(min = 2, max = 30, message = "Patronymic не может быть короче 2-х и длиньше 30 символов")
    private String patronymic;
    @Min(value = 0, message = "Age не может быть меньше 0")
    private int age;
    @Min(value = 0, message = "Salary не может быть меньше 0")
    private double salary;
    @NotEmpty(message = "Поле Email не может быть пустым")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Введите верный формат email")
    private String email;
    @NotEmpty(message = "Поле Location не может быть пустым")
    private String location;

    public Employee() {
    }

    public Employee(String surname, String name, String patronymic, int age, double salary, String email, String location) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.age = age;
        this.salary = salary;
        this.email = email;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
