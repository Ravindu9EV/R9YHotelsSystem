import edu.icet.Main;
import edu.icet.dto.RoomDTO;
import edu.icet.entity.Room;
import edu.icet.repo.RoomRepo;
import edu.icet.service.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = Main.class)
public class RoomServiceImplTest {
    @Autowired
    private RoomServiceImpl service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private RoomRepo repo;

    //Test Save method
    @Test
    void shouldReturnMessageThatSayingSavingWasSuccess(){
        RoomDTO roomDTO=new RoomDTO(0l,"R001",false,4,7500.00);
        ResponseEntity<String> response=service.addRoom(roomDTO);
        System.out.println(response);
        assertEquals(200,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenSaveWithNullObject(){

        //  RoomDTO roomDTO=new RoomDTO(1l,"R002",false,4,7600.00);

        ResponseEntity<String> response=service.addRoom(null);
        System.out.println(response);
        assertEquals(400,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenSavingRoomWith_empty_roomNumber(){
        RoomDTO roomDTO=new RoomDTO(0l,"",false,4,7500.00);
        ResponseEntity<String> response=service.addRoom(roomDTO);
        System.out.println(response);
        assertEquals(400,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenSavingRoomWith_maxOccupancyIsZero(){
        RoomDTO roomDTO=new RoomDTO(0l,"",false,0,7500.00);
        ResponseEntity<String> response=service.addRoom(roomDTO);
        System.out.println(response);
        assertEquals(400,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenSavingRoomWith_maxOccupancyIsMinus(){
        RoomDTO roomDTO=new RoomDTO(0l,"",false,-1,7500.00);
        ResponseEntity<String> response=service.addRoom(roomDTO);
        System.out.println(response);
        assertEquals(400,response.getStatusCodeValue());
    }

    @Test
    void shouldReturnErrorWhenSavingRoomWith_PriceIsZero(){
        RoomDTO roomDTO=new RoomDTO(0l,"",false,4,0);
        ResponseEntity<String> response=service.addRoom(roomDTO);
        System.out.println(response);
        assertEquals(400,response.getStatusCodeValue());
    }

    @Test
    void shouldReturnErrorWhenSavingRoomWith_MinusPrice(){
        RoomDTO roomDTO=new RoomDTO(0l,"",false,4,-10.23);
        ResponseEntity<String> response=service.addRoom(roomDTO);
        System.out.println(response);
        assertEquals(400,response.getStatusCodeValue());
    }

    //test update method
    @Test
    void shouldReturnMessageThatUpdateWasSuccess(){

        RoomDTO roomDTO=new RoomDTO(1l,"R002",false,4,7600.00);

        ResponseEntity<String> response=service.editRoom(roomDTO);
        System.out.println(response);
        assertEquals(200,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenUpdateWithNullObject(){

        ResponseEntity<String> response=service.editRoom(null);
        System.out.println(response);
        assertEquals(422,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenUpdateWithEmptyRoomNumber(){

         RoomDTO roomDTO=new RoomDTO(1l,"",false,4,7600.00);

        ResponseEntity<String> response=service.editRoom(null);
        System.out.println(response);
        assertEquals(422,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenUpdateWithMaxOccupancyIsZero(){

         RoomDTO roomDTO=new RoomDTO(1l,"R002",false,0,7600.00);

        ResponseEntity<String> response=service.editRoom(null);
        System.out.println(response);
        assertEquals(422,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenUpdateWithMaxOccupancyIsMinus(){

        RoomDTO roomDTO=new RoomDTO(1l,"R002",false,-10,7600.00);

        ResponseEntity<String> response=service.editRoom(null);
        System.out.println(response);
        assertEquals(422,response.getStatusCodeValue());
    }
    @Test
    void shouldReturnErrorWhenUpdateWithPriceIsZero(){

        RoomDTO roomDTO=new RoomDTO(1l,"R002",false,10,0);

        ResponseEntity<String> response=service.editRoom(null);
        System.out.println(response);
        assertEquals(422,response.getStatusCodeValue());
    }

    @Test
    void shouldReturnErrorWhenUpdateWithPriceIsMinus(){

        RoomDTO roomDTO=new RoomDTO(1l,"R002",false,10,-7600.00);

        ResponseEntity<String> response=service.editRoom(null);
        System.out.println(response);
        assertEquals(422,response.getStatusCodeValue());
    }

    //test find room
    @Test
    void shouldReturnRoomWhenRoomIdIsCorrect(){

        RoomDTO roomDTO=new RoomDTO(1l,"R002",false,4,7600.00);

        RoomDTO response=service.findById(1l);
        System.out.println(response);
        assertEquals(roomDTO,response);
    }
    @Test
    void shouldReturnNullWhenRoomNotFound(){

      //  RoomDTO roomDTO=new RoomDTO(1l,"R002",false,4,7600.00);

        RoomDTO response=service.findById(4l);
        System.out.println(response);
        assertEquals(null,response);
    }
    @Test
    void shouldReturnNullWhenIdIsNull(){

        //  RoomDTO roomDTO=new RoomDTO(1l,"R002",false,4,7600.00);

        RoomDTO response=service.findById(null);
        System.out.println(response);
        assertEquals(null,response);
    }
    @Test
    void shouldReturnNullWhenIdIsZero(){

        //  RoomDTO roomDTO=new RoomDTO(1l,"R002",false,4,7600.00);

        RoomDTO response=service.findById(0l);
        System.out.println(response);
        assertEquals(null,response);
    }

    @Test
    void shouldReturnNullWhenIdIsMinus(){

        //  RoomDTO roomDTO=new RoomDTO(1l,"R002",false,4,7600.00);

        RoomDTO response=service.findById(-1l);
        System.out.println(response);
        assertEquals(null,response);
    }
}
