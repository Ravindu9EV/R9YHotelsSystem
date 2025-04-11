package edu.icet.config;

import edu.icet.service.MyUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {


    private  final JwtTokenProvider jwtTokenProvider;


    //private final ApplicationContext context;


    private final MyUserDetailService userDetailService;


//    public JwtAuthFilter(JwtTokenProvider jwtTokenProvider){
//        this.jwtTokenProvider=jwtTokenProvider;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header=request.getHeader("Authorization");

        if(header!= null && header.startsWith("Bearer ")){
            String token= header.substring(7);
            String username=jwtTokenProvider.getUsernameFormToken(token);
            UserDetails userDetails=userDetailService.loadUserByUsername(username);

            if(jwtTokenProvider.validateToken(token,userDetails)){
                Authentication auth = new UsernamePasswordAuthenticationToken(username,null,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request,response);
    }
}
