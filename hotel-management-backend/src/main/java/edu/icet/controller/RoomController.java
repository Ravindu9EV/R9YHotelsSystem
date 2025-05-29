package edu.icet.controller;

import edu.icet.config.JwtTokenProvider;
import edu.icet.dto.RoomDTO;
import edu.icet.service.MyUserDetailService;
import edu.icet.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    @Autowired
    RoomService service;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    MyUserDetailService myUserDetailService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> save(@RequestBody RoomDTO roomDTO, HttpServletRequest request){
        System.out.println(roomDTO);
        System.out.println("before: "+roomDTO);
        String authHeader= request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid token");
        }
        String token=authHeader.substring(7);
        System.out.println(tokenProvider.getExpirationDateFromToken(token));
        String username;
        try{
            if(tokenProvider.isTokenExpired(token)){
                System.out.println("Exxx");
                return ResponseEntity.status(401).body("Expierd");
            }
            username=tokenProvider.getUsernameFormToken(token);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");

        }

        UserDetails user= myUserDetailService.loadUserByUsername(username);
        if(tokenProvider.isTokenExpired(token)){

            System.out.println("Token Expired");

            return ResponseEntity.status(401).body("Token Expired");
        }
        if(!tokenProvider.validateToken(token,user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token validation failed");
        }

        return service.addRoom(roomDTO);
    }
    @PutMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> edit(@RequestBody RoomDTO roomDto){
        return service.editRoom(roomDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public boolean delete(@RequestParam Long id){
        return service.deleteRoom(id);
    }

    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomDTO find(@RequestParam Long id){
        return service.findById(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomDTO> findAvailableRooms(){
        return service.getAvailableRooms();
    }
}
