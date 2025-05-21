package edu.icet.service;

import edu.icet.dto.RoomDTO;
import edu.icet.entity.Room;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoomService {
    ResponseEntity<String> addRoom(RoomDTO roomDTO);
    ResponseEntity<String> editRoom(RoomDTO roomDTO);
    boolean deleteRoom(Long id);
    RoomDTO findById(Long id);
    List<RoomDTO> getAll();
    List<RoomDTO> getAvailableRooms();

}
