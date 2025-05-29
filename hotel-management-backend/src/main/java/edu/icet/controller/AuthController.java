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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
   @Autowired
    AuthenticationManager authenticationManager;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest request){

        try{
            Authentication authentication=authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );

            UserDetails userDetails= myUserDetailService.loadUserByUsername(request.getUsername());
            String token=tokenProvider.generateToken(userDetails);
            System.out.println("Generated Token: "+token);
            System.out.println(token+"--"+tokenProvider.validateToken(token,userDetails));
            AuthResponse response=new AuthResponse(userService.authenticate(request));
            response.setToken(token);
            return ResponseEntity.ok().header("Authorization","Bearer "+token).body(response);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Authentication failed: "+e.getMessage()));
        }



    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
       try{
           userService.saveUser(request);
           return ResponseEntity.ok("User Registered Succesfully!");
       }catch (Exception e){
           return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body("Registration Failed: "+e.getMessage());
       }
    }
}
