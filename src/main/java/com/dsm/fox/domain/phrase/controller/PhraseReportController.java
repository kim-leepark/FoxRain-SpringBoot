package com.dsm.fox.domain.phrase.controller;

import com.dsm.fox.domain.phrase.rqrs.PhraseReportRs;
import com.dsm.fox.domain.phrase.rqrs.PhraseReportContentRq;
import com.dsm.fox.domain.phrase.service.PhraseReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phrase")
public class PhraseReportController {

    private final PhraseReportService reportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/report/{phraseId}")
    public void phraseReport(@PathVariable int phraseId, @RequestBody PhraseReportContentRq rq) {
        reportService.phraseReport(phraseId, rq.getContent());
    }

    @GetMapping("/reports")
    public List<PhraseReportRs> getPhrases(final Pageable pageable) {
        return reportService.getPhraseList(pageable);
    }
}
