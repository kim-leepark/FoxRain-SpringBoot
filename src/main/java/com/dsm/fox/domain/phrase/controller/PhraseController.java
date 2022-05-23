package com.dsm.fox.domain.phrase.controller;

import com.dsm.fox.domain.phrase.rqrs.PhraseCreateRq;
import com.dsm.fox.domain.phrase.rqrs.PhraseRs;
import com.dsm.fox.domain.phrase.service.PhraseService;
import com.dsm.fox.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phrase")
public class PhraseController {
    private final PhraseService phraseService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void phraseRegistration(@AuthenticationPrincipal final User user, @RequestBody @Validated PhraseCreateRq rq) {
        phraseService.phraseRegistration(rq, user);
    }

    @GetMapping
    public PhraseRs randomPhrase(@AuthenticationPrincipal final User user) {
        return phraseService.randomPhrase(user);
    }


}
