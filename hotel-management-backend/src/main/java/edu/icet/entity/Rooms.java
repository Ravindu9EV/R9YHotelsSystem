package edu.icet.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double price;
    private boolean isBooked;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = true)
    private User bookedBy;
}
