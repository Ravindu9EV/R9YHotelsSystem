package edu.icet.entity;

import edu.icet.util.BookingType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "rooms")
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double price;
    private boolean isBooked;
    private String roomNumber;
    private Integer maxOccupancy;
//    @ManyToOne
//    @JoinColumn(name="user_id",nullable = true)
//    private User bookedBy;
}
