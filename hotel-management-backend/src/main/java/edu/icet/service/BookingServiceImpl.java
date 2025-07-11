package edu.icet.service;

import edu.icet.dto.BookingDTO;
import edu.icet.entity.Booking;
import edu.icet.entity.User;
import edu.icet.repo.BookingRepo;
import edu.icet.repo.UserRepository;
import edu.icet.util.BookingType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService{
    private final BookingRepo repo;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    @Override
    public ResponseEntity<String> save(BookingDTO bookingDTO) {
        System.out.println(bookingDTO);

        try{
            if( bookingDTO.getFromDate()==null||bookingDTO.getFromDate().isEmpty()||bookingDTO.getToDate()==null|| bookingDTO.getToDate().isEmpty()||bookingDTO.getBookingType()==null || bookingDTO.getBookingType().describeConstable().isEmpty()||bookingDTO.getPrice()==null||bookingDTO.getPrice()<=0||bookingDTO.getDescription()==null ||bookingDTO.getDescription().isEmpty()){
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing field");
            }
            Optional<User> user =userRepository.findUserByUsername(bookingDTO.getUsername());
            System.out.println(user.get());
            Booking booking=new Booking(null,bookingDTO.getBookingType(),setDate(bookingDTO.getFromDate()),setDate(bookingDTO.getToDate()),bookingDTO.getNumberOfPeople(),bookingDTO.getPrice(),bookingDTO.getDescription(),bookingDTO.getStatus(),user.get());
           booking=repo.save(booking);
            System.out.println("saved:"+booking);
            return ResponseEntity.ok("{\"message\":\"saved Success!\"}");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());

        }
    }

    @Override
    public ResponseEntity<BookingDTO> edit(BookingDTO bookingDTO) {
        return null;
    }

    @Override
    public ResponseEntity<BookingDTO> searchById(Long id) {
        return null;
    }

    @Override
    public List<BookingDTO> searchByBookingType(BookingType bookingType) {
        return List.of();
    }

    @Override
    public List<BookingDTO> getAll() {
        return List.of();
    }

    @Override
    public ResponseEntity<String> delete(Long id) {
        return null;
    }

    private Date setDate(String date){
        Integer year=Integer.parseInt(date.substring(0,4));
        Integer month=Integer.parseInt(date.substring(4,7));
        Integer day=Integer.parseInt(date.substring(7,9));

        return new Date(year,month,day);
    }
}
