//import edu.icet.Main;
//import edu.icet.dto.BookingDTO;
//import edu.icet.entity.Booking;
//import edu.icet.repo.BookingRepo;
//import edu.icet.service.BookingService;
//import edu.icet.service.BookingServiceImpl;
//import edu.icet.util.BookingStatus;
//import edu.icet.util.BookingType;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.modelmapper.ModelMapper;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(classes = Main.class)
//public class BookingServiceImplTest {
//    @Autowired
//    private BookingServiceImpl bookingService;
//    @Autowired
//    BookingRepo repo;
//
//    @Autowired
//    ModelMapper mapper;
//
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithEmptyFromDate(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",5,45.4,"asd", BookingStatus.PENDING,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithEmptyToDate(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"2025-04-14","",5,45.4,"asd",BookingStatus.APPROVED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithNumberOfPeopleIsNull(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",null,45.4,"asd",BookingStatus.PENDING,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithNumberOfPeopleIsLessThanZero(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",-1,45.4,"asd",BookingStatus.CANCELED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithNumberOfPeopleIsEqualsZero(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",0,45.4,"asd",BookingStatus.APPROVED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithPriceIsNull(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",4,null,"asd",BookingStatus.CANCELED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithPriceIsLessThanZero(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",4,-1.0,"asd",BookingStatus.CANCELED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithPriceIsZero(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",4,0.0,"asd",BookingStatus.CANCELED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithNullDescription(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",4,5.6,null,BookingStatus.CANCELED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnErrorWhenSaveBookingWithEmptyDescription(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"","sa",4,5.6,"",BookingStatus.CANCELED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("Missing field",response.getBody());
//
//    }
//    @Test
//    void ShouldReturnSuccessMsgWhenSaveBookingWithValidObject(){
//        BookingDTO bookingDTO=new BookingDTO(0L, BookingType.ROOM,"2025-04-14","2025-04-15",5,45.4,"asd",BookingStatus.CANCELED,"Yasantha");
//        ResponseEntity<String> response=bookingService.save(bookingDTO);
//        assertEquals("saved Success!",response.getBody());
//
//    }
//}
