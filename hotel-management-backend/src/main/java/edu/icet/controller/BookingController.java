package edu.icet.controller;

import edu.icet.config.JwtTokenProvider;
import edu.icet.dto.BookingDTO;
import edu.icet.entity.Booking;
import edu.icet.service.BookingService;
import edu.icet.service.MyUserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/booking")
public class BookingController {
    @Autowired
    MyUserDetailService myUserDetailService;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    BookingService bookingService;
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<String> save(@RequestBody BookingDTO bookingDTO, HttpServletRequest request){
        String authHeader= request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid token");
        }
        String token=authHeader.substring(7);
        System.out.println(tokenProvider.getExpirationDateFromToken(token));
        String username;
        try{
            username=tokenProvider.getUsernameFormToken(token);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");

        }
      UserDetails user=myUserDetailService.loadUserByUsername(username);
        if(!tokenProvider.validateToken(token,user)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token validation failed");
        }

        System.out.println(bookingDTO);
        return bookingService.save(bookingDTO);
    }
}
