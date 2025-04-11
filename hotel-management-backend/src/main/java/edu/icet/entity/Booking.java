package edu.icet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookings")
public class Booking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private enum type;
}
