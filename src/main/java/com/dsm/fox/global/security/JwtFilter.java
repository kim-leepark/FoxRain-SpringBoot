package com.dsm.fox.global.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        System.out.println(token);

        if(token!=null && jwtUtil.validateToken(token)) {
//            Authentication authentication = jwtUtil.getAuthentication(token);
            Authentication authentication = jwtUtil.getAuthenticationAndUser(token);
            log.info("authentication pass");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token!=null && token.startsWith("Bearer")) {
            return token.substring(7);
        }
        return null;
    }
}
