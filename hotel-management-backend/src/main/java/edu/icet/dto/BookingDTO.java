package edu.icet.dto;

import edu.icet.util.BookingStatus;
import edu.icet.util.BookingType;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingDTO {
    private Long id;
    private BookingType bookingType;
    private String fromDate;
    private String toDate;
    private Integer numberOfPeople;
    private Double price;
    private String description;
    private BookingStatus status;
    private Long user_id;
}
