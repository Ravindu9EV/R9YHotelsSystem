package edu.icet.service;

import edu.icet.config.JwtTokenProvider;
import edu.icet.dto.LoginRequest;
import edu.icet.dto.RegisterRequest;
import edu.icet.entity.Booking;
import edu.icet.entity.User;
import edu.icet.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

//import static jdk.internal.org.jline.reader.impl.LineReaderImpl.CompletionType.List;

@Service
public class UserServiceImpl implements UserService{
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository repository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenPovider;


    @Override
    public void saveUser(RegisterRequest request) {
        try{
            repository.save(new User(0l,request.getUsername(), passwordEncoder.encode(request.getPassword()), request.getRole(),new ArrayList<Booking>()));
            System.out.println(request);
        }catch (Exception e){
            throw new RuntimeException("Null Object!",e);
        }
    }

    @Override
    public String authenticate(LoginRequest request) {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

            UserDetails user=repository.findByUsername(request.getUsername()).orElseThrow(()->new UsernameNotFoundException("User Not Found!"));

            return jwtTokenPovider.generateToken(user);

        }catch (Exception e){
            return "";
        }
    }


}
