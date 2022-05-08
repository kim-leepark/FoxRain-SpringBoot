package com.dsm.fox.domain.phrase.controller;

import com.dsm.fox.domain.phrase.rqrs.PhraseReportRs;
import com.dsm.fox.domain.phrase.rqrs.PhraseReportContentRq;
import com.dsm.fox.domain.phrase.rqrs.ReportPhraseRs;
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

    @GetMapping("/report/{id}") // 신고 사유
    public List<PhraseReportRs> getPhrasesReason(@PathVariable final int id) {
        return reportService.getPhraseReportReason(id);
    }

    // 신고 리스트
    @GetMapping("/reports")
    public List<ReportPhraseRs> getReportPhrase(Pageable pageable){
        return reportService.getReportPhrases(pageable);
    }
}
