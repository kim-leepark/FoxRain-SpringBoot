package com.dsm.fox.global.security;

import com.dsm.fox.domain.admin.Admin;
import com.dsm.fox.domain.admin.AdminRepository;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.global.exception.exceptions.AdminNotFoundException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${auth.secret.admin}")
    private String adminSecret;
    @Value("${auth.secret.user}")
    private String userSecret;
    @Value("${auth.time}")
    private long time;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public String createAdminToken(int id, String name) {
        Key key = Keys.hmacShaKeyFor(adminSecret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .claim("id",id)
                .claim("name",name)
                .setSubject("admin")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+time*60*60*24*12))
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
                .setExpiration(new Date(System.currentTimeMillis()+time*60*24*12))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Admin getAdmin(String token) {
        return adminRepository.findByName(getAdminInfo(token, "name")).orElseThrow(AdminNotFoundException::new);
    }

    public String getAdminInfo(String token, String type) {
        return (String) getClaims(token, adminSecret).get(type);
    }

    public String getUserInfo(String token, String type) {
        return (String) getClaims(token, userSecret).get(type);
    }

    public boolean adminCheck(String token) {
        return getClaims(token, adminSecret).getSubject().equals("admin");
    }

    public Claims getClaims(String token, final String secret) {
        Key key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(secret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody();
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

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(userSecret.getBytes(StandardCharsets.UTF_8)).build().parseClaimsJws(token).getBody();
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
