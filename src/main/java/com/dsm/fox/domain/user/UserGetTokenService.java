package com.dsm.fox.domain.user;

import com.dsm.fox.domain.admin.rqrs.AccessTokenRs;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import com.dsm.fox.global.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserGetTokenService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AccessTokenRs createToken(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);

        return AccessTokenRs.builder().accessToken(jwtUtil.createUserToken(email,user.getId())).build();
    }
}
