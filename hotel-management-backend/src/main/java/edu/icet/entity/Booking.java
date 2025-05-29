package edu.icet.entity;

import edu.icet.util.BookingStatus;
import edu.icet.util.BookingType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "bookings")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BookingType bookingType;
    private Date fromDate;
    private Date toDate;
    private Integer numberOfPeople;
    private Double price;
    private String description;
    private BookingStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
}
