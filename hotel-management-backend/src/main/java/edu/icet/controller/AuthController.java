package edu.icet.controller;


import edu.icet.dto.AuthResponse;
import edu.icet.dto.LoginRequest;
import edu.icet.dto.RegisterRequest;
import edu.icet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {
   @Autowired
    UserService userService;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest request){
        System.out.println(request);
        return ResponseEntity.ok(new AuthResponse(userService.authenticate(request)));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        userService.saveUser(request);
        return ResponseEntity.ok("User Registered Succesfully!");
    }
}
