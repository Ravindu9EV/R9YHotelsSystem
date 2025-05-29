package edu.icet.config;

import io.jsonwebtoken.*;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {
    //@Value("${jwt.expiration}")
    private long expirationTime;

//    @Value("${jwt.secret}")
     // private  SecretKey secretKey;
//

    private PrivateKey privateKey;
    private PublicKey publicKey;

//    public JwtTokenProvider(SecretKey secretKey){
//        this.secretKey=generateSecretKey();
//    }
    @PostConstruct
    public void init(){
//        secretKey=generateSecretKey();
        try{
            KeyPairGenerator keyGen=KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair=keyGen.generateKeyPair();
            this.privateKey=keyPair.getPrivate();
            this.publicKey=keyPair.getPublic();

        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    //Generate a dynamic key
    private SecretKey  generateSecretKey(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            keyGen.init(256);
            SecretKey secretKey=keyGen.generateKey();
            return secretKey;
            //return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        }catch (Exception e){
            throw new RuntimeException("Error generating secret key");
        }
    }
    // generate Token
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+7*24*60*60*1000))
                //.signWith(SignatureAlgorithm.HS256,secretKey)
                .signWith(privateKey,SignatureAlgorithm.RS256)
                .compact();
    }

    //Extract Token expireDate
    public Date getExpirationDateFromToken(String token){
        try{
            JwtParser jwtParser= Jwts.parser()
                    .setSigningKey(publicKey).build();

                 return    jwtParser.parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        }catch(ExpiredJwtException e) {
            //refresh token if expired
            throw new RuntimeException("Token Expired",e);
        }catch (JwtException e){
            throw new RuntimeException("Invalid Token",e);
        }

    }
    //get username
    public String getUsernameFormToken(String token){
        try{
            JwtParser jwtParser = Jwts.parser()
                    .setSigningKey(publicKey)
                    .build();
            return jwtParser.parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch(ExpiredJwtException e){
            throw new RuntimeException(e.getMessage());
        } catch(JwtException e){
            throw new RuntimeException("Invalid or Expired JWT token",e);
        }
    }
    //validate token
    public boolean validateToken(String token,UserDetails userDetails){
//        final String username=getUsernameFormToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
       try{
            Claims claims=Jwts.parser().setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject().equals(userDetails.getUsername());
        }catch (ExpiredJwtException e){
            return false;
        }catch (JwtException e){
            return false;
        }
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }


}
