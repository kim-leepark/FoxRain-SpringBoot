package com.dsm.fox.domain.phrase.controller;

import com.dsm.fox.domain.phrase.rqrs.ReportReasonRs;
import com.dsm.fox.domain.phrase.rqrs.PhraseReportContentRq;
import com.dsm.fox.domain.phrase.rqrs.ReportPhraseRs;
import com.dsm.fox.domain.phrase.service.PhraseReportService;
import com.dsm.fox.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PhraseReportController {

    private final PhraseReportService reportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/phrase/{id}/report")
    public void phraseReport(@AuthenticationPrincipal final User user, @PathVariable("id") int phraseId, @RequestBody PhraseReportContentRq rq) {
        reportService.phraseReport(phraseId, rq.getContent(), user);
    }

    @GetMapping("/phrase/{id}/report/reason")
    public List<ReportReasonRs> getPhrasesReason(@PathVariable final int id) {
        return reportService.getPhraseReportReason(id);
    }

    @GetMapping("/report/phrases")
    public List<ReportPhraseRs> getReportPhrase(Pageable pageable){
        return reportService.getReportPhrases(pageable);
    }
}
