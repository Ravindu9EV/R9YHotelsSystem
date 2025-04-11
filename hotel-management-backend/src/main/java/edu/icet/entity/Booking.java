package edu.icet.entity;

import edu.icet.util.BookingType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "bookings")
@Setter
@Getter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BookingType type;
    private Date fromDate;
    private Date toDate;
    private Integer numberOfPeople;
    private Double price;
    private String description;

}
