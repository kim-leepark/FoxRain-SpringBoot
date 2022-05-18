package com.dsm.fox.domain.user;

import com.dsm.fox.domain.admin.rqrs.AccessTokenRs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserGetTokenController {
    private final UserGetTokenService userLoginService;

    @PostMapping("/token")
    public AccessTokenRs createToken(@RequestBody UserTokenRq rq) {
        return userLoginService.createToken(rq.getEmail());
    }
}
