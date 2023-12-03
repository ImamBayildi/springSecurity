package com.labreport.backendlabrep.service;

import java.security.Key;// import org.springframework.boot.autoconfigure.ssl.SslBundleProperties.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;// import lombok.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    @Value("${jwt.key}")
    private String SecretKEY;// = "mYiADZOy9Hq5pTfomhj9hPUJLGAN/ZB6AZ4yoHfCtN0f8DaNQr6+4qeaJ9YfMK4ZbX85J3ao6TotW+jrLKLGeafpaYYLn2mmrYOB/1mJzhQYEaf883PtWAIwnTwHpPuEsJAhyHwEtlTvy++pZA+0bns66RUDBk80bJUA4JhJUI8=";

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }
    

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUser(token);
        Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals(username) && !expirationDate.before(new Date());
    }

    private Date extractExpiration(String token) {
        Claims claims = Jwts
        .parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
        return claims.getExpiration();
    }

    public String extractUser(String token){
        Claims claims = Jwts
        .parserBuilder()
        .setSigningKey(getSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
        return claims.getSubject();
    }

    public String createToken(Map<String, Object> claims, String username) {
        return Jwts
        .builder()
        .setClaims(claims)
        .setSubject(username)
        .setIssuedAt(new Date(System.currentTimeMillis()))  //second=1000ms
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))//10 hour
        .signWith(getSignKey(), SignatureAlgorithm.HS256)//Which one?
        .compact();
    }





    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SecretKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
