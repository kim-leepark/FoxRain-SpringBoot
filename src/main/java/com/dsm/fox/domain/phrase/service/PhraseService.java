package com.dsm.fox.domain.phrase.service;

import com.dsm.fox.domain.phrase.Phrase;
import com.dsm.fox.domain.phrase.PhraseRepository;
import com.dsm.fox.domain.phrase.report.PhraseReport;
import com.dsm.fox.domain.phrase.report.PhraseReportRepository;
import com.dsm.fox.domain.phrase.rqrs.PhraseCreateRq;
import com.dsm.fox.domain.phrase.rqrs.PhraseRs;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.global.exception.BasicException;
import com.dsm.fox.global.exception.exceptions.PhraseNotFoundException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;
    private final PhraseReportRepository reportRepository;

    public void phraseRegistration(PhraseCreateRq rq, User user) {
        phraseRepository.save(
                Phrase.builder()
                    .content(rq.getContent())
                    .man(rq.getMan(user.getName()))
                    .user(user)
                    .build()
        );
    }

    public PhraseRs randomPhrase(User user) {
        Phrase phrase = phraseRepository.findRandomPhrase();
        return PhraseRs.builder()
                .id(phrase.getId())
                .content(phrase.getContent())
                .man(phrase.getMan())
                .isReport(userReportCheck(user, phrase.getId()))
                .build();
    }

    public boolean userReportCheck(User user, int phraseId) {
        if(user==null) {
            return false;
        }
        return reportRepository.existsByUserIdAndPhraseId(user.getId(), phraseId);
    }

}
