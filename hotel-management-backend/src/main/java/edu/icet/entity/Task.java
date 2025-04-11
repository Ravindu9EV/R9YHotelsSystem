package edu.icet.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String status;
    private String description;
    private LocalDateTime assignedAt;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;
}
