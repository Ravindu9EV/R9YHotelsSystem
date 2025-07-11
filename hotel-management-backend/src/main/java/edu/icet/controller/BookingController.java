package edu.icet.controller;

import edu.icet.config.JwtTokenProvider;
import edu.icet.dto.BookingDTO;
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
        System.out.println("before: "+bookingDTO);
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

//        bookingDTO.setUser_id();

        System.out.println(bookingDTO);
        return bookingService.save(bookingDTO);
    }
}
