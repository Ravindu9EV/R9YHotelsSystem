package edu.icet.service;

import edu.icet.dto.BookingDTO;
import edu.icet.util.BookingType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookingService {
    ResponseEntity<String> save(BookingDTO bookingDTO);
    ResponseEntity<BookingDTO> edit(BookingDTO bookingDTO);
    ResponseEntity<BookingDTO> searchById(Long id);
    List<BookingDTO> searchByBookingType(BookingType bookingType);
    List<BookingDTO> getAll();
    ResponseEntity<String> delete(Long id);
}
