package com.dsm.fox.phrase.controller;

import com.dsm.fox.phrase.rsrq.PhraseCreateRq;
import com.dsm.fox.phrase.service.PhraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/phrase")
public class PhraseController {
    private final PhraseService phraseService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void phraseRegistration(@RequestBody @Validated PhraseCreateRq rq) {
        phraseService.phraseRegistration(rq);
    }

    @GetMapping
    public void randomPhrase() {

    }

}
