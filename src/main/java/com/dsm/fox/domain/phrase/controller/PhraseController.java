package com.dsm.fox.domain.phrase.controller;

import com.dsm.fox.domain.phrase.rqrs.PhraseCreateRq;
import com.dsm.fox.domain.phrase.rqrs.PhraseReportContentRq;
import com.dsm.fox.domain.phrase.rqrs.PhraseRs;
import com.dsm.fox.domain.phrase.service.PhraseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
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
    public PhraseRs randomPhrase() {
        return phraseService.randomPhrase();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/report/{phraseId}")
    public void phraseReport(@PathVariable int phraseId, @RequestBody PhraseReportContentRq rq) {
        phraseService.phraseReport(phraseId, rq.getContent());
    }
}
