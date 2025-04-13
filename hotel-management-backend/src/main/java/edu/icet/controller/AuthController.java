package edu.icet.controller;


import edu.icet.config.JwtTokenProvider;
import edu.icet.dto.AuthResponse;
import edu.icet.dto.LoginRequest;
import edu.icet.dto.RegisterRequest;
import edu.icet.service.MyUserDetailService;
import edu.icet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {
   @Autowired
    UserService userService;

   @Autowired
   MyUserDetailService myUserDetailService;

   @Autowired
   JwtTokenProvider tokenProvider;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest request){
      UserDetails userDetails= myUserDetailService.loadUserByUsername(request.getUsername());
      String to=tokenProvider.generateToken(userDetails);
        System.out.println(to+"--"+tokenProvider.validateToken(to,userDetails));
        return ResponseEntity.ok(new AuthResponse(userService.authenticate(request)));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        userService.saveUser(request);
        return ResponseEntity.ok("User Registered Succesfully!");
    }
}
