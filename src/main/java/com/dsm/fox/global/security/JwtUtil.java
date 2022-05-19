package com.dsm.fox.global.security;

import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtUtil {
    @Value("{auth.secret}")
    private String adminSecret;
//    @Value("{auth.secret}")
    private String userSecret="FASDFSGW1asdf31!!@#!asdfagdDFSGW1asdf31!";
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public String createAdminToken(String id) {
        Key key = Keys.hmacShaKeyFor(adminSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claim("id",id)
                .claim("name","")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+6000*60*24*12))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createUserToken(String email, int id) {
        Key key = Keys.hmacShaKeyFor(userSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claim("email",email )
                .claim("id",id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+6000*60*24*12))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public String getIdFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(adminSecret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return (String) claims.get("id");
    }

    public String getNameFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(adminSecret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("user");
    }

    public boolean validateToken(String token){
        return true;

    }

    public String getEmail(String token) {
        Key key = Keys.hmacShaKeyFor(userSecret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("email");
    }

    public Authentication getAuthentication(String token) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public User getUser(String token) {
        return userRepository.findByEmail(getEmail(token)).orElseThrow(UserNotFoundException::new);
    }

    public Authentication getAuthenticationAndUser(String token) {
        User user = getUser(token);
        return new UsernamePasswordAuthenticationToken(user, null,null);
    }

}
