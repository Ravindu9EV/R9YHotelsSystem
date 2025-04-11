package edu.icet.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private String contact;
    private String address;

    @OneToMany(mappedBy = "employee")
    private List<Task> tasks;
}
