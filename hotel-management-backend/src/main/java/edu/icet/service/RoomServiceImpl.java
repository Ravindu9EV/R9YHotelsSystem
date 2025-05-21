package edu.icet.service;

import edu.icet.dto.RoomDTO;
import edu.icet.entity.Room;
import edu.icet.entity.User;
import edu.icet.repo.RoomRepo;
import edu.icet.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService{

    private static final Logger log = LoggerFactory.getLogger(RoomServiceImpl.class);
    private final RoomRepo roomRepo;
    final ModelMapper mapper;
    final UserRepository userRepository;

    //Save room
    @Override
    public ResponseEntity<String> addRoom(RoomDTO roomDTO) {
        try{

            if(roomDTO!=null&& roomDTO.getRoomNumber().isEmpty()==false && roomDTO.is_booked() == false && roomDTO.getPrice() > 0){
                Room room=mapper.map(roomDTO,Room.class);
                System.out.println(room);
                room.setId(null);
               Room r= roomRepo.save(room);
                System.out.println(r);
                return ResponseEntity.ok("Room Added Successfully!");
            }
        }catch (Exception e){
            log.info(e.getMessage());

        }
        return ResponseEntity.badRequest().body("Failed to Added the Room");
    }

    //update room
    @Override
    public ResponseEntity<String> editRoom(RoomDTO roomDTO) {
        try {
            System.out.println(roomDTO);
            if (roomDTO!=null && roomDTO.getId() > 0 && roomDTO.getRoomNumber().isEmpty()==false && roomDTO.is_booked() == false && roomDTO.getPrice() > 0 ) {
                //find room
                Room room =roomRepo.findById(roomDTO.getId()).get();
                System.out.println("find:"+room);
               if (room != null) {
                    Room newR=mapper.map(roomDTO,Room.class);
                    Room r=roomRepo.save(newR);
                    System.out.println(r);
                    return ResponseEntity.ok("Room Successfully Updated!");
                }
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.status(400).body("Failed to Save");
        }
        return ResponseEntity.unprocessableEntity().body("Failed to Save");
    }



    //delete room
    @Override
    public boolean deleteRoom(Long id) {
        try{
            roomRepo.deleteById(id);
            return true;
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return false;
    }

    //find room by id
    @Override
    public RoomDTO findById(Long id) {
        try{
            Room room =roomRepo.findById(id).get();
            System.out.println("f"+room);
            if(room!=null) {
                //RoomDTO roomDTO = ;
                return mapper.map(room, RoomDTO.class);
            }
        }catch (Exception e){
            log.info(e.getMessage());

        }
        return null;
    }

    //get all rooms
    @Override
    public List<RoomDTO> getAll() {
        List<RoomDTO> rooms=new ArrayList<>();
        for(Room room : roomRepo.findAll()){
            if(room!=null){
                rooms.add(mapper.map(room,RoomDTO.class));
            }
        }
        return rooms;
    }

    //filter available rooms
    @Override
    public List<RoomDTO> getAvailableRooms() {
        List<RoomDTO> availableRooms=new ArrayList<>();
        for(Room room:roomRepo.findAll()){
            if(room!=null && room.isBooked()==false){
                availableRooms.add(mapper.map(room,RoomDTO.class));
            }
        }
        return availableRooms;
    }


}
