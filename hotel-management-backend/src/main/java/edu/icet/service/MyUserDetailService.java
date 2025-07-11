package edu.icet.service;

import edu.icet.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{


            UserDetails user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User not found! "+username));

            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),user.getPassword(),new ArrayList<>());
        }catch (Exception e){
            throw new UsernameNotFoundException("User Not found!"+username);
        }


    }
}
