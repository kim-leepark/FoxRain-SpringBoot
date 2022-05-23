package com.dsm.fox.global.security;

import com.dsm.fox.domain.admin.Admin;
import com.dsm.fox.domain.admin.AdminRepository;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.global.exception.exceptions.AdminNotFoundException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtUtil {
//    @Value("{auth.key}")
    private String adminSecret="FASDFSGW1asdf31!!@#!asdfagdDFSGW1asdf31!";
//    @Value("{auth.secret}")
    private String userSecret="FASDFSGW1asdf31!!@#!asdfagdDFSGW1asdf31!";
    private int time;
    private final CustomUserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public String createAdminToken(int id, String name) {
        Key key = Keys.hmacShaKeyFor(adminSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claim("id",id)
                .claim("name",name)
                .setSubject("admin")
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
                .setSubject("user")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+6000*60*24*12))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public int getIdFromToken(String token) {
        Key key = Keys.hmacShaKeyFor(adminSecret.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(key)
                            .build()
                            .parseClaimsJws(token)
                            .getBody();
        return (int) claims.get("id");
    }

    public boolean validateToken(String token){
        //악...
        return true;
    }

    public String getAdminInfo(String token, String type) {
        return (String) getClaims(token, adminSecret).get(type);
    }
    public String getUserInfo(String token, String type) {
        return (String) getClaims(token, userSecret).get(type);
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



    public User getUser(String token) {
        return userRepository.findByEmail(getUserInfo(token, "email")).orElseThrow(UserNotFoundException::new);
    }

    public Authentication getAuthenticationAndUser(String token) {
        User user = getUser(token);
        return new UsernamePasswordAuthenticationToken(user, null,createAuth("ROLE_USER"));
    }

    public Authentication getAuthenticationAndAdmin(String token) {
        Admin admin = getAdmin(token);
        return new UsernamePasswordAuthenticationToken(admin, null, createAuth("ROLE_ADMIN"));
    }

    public List<GrantedAuthority> createAuth(final String auth) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new CustomGrantedAuthority(auth));
        return grantedAuthorities;
    }

    public Admin getAdmin(String token) {
        return adminRepository.findByName(getAdminInfo(token, "name")).orElseThrow(AdminNotFoundException::new);
    }

    public boolean adminCheck(String token) {
        return getClaims(token, adminSecret).getSubject().equals("admin");
    }

    public Claims getClaims(String token, final String secret) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Authentication getAuthentication(String token) {
        CustomUserDetails userDetails = userDetailsService.loadUserByUsername(getUserInfo(token, "email"));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
}
