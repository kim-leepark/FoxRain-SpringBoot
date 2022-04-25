package com.dsm.fox.domain.phrase.service;

import com.dsm.fox.domain.phrase.rqrs.PhraseReportRs;
import com.dsm.fox.domain.phrase.Phrase;
import com.dsm.fox.domain.phrase.PhraseRepository;
import com.dsm.fox.domain.phrase.report.PhraseReport;
import com.dsm.fox.domain.phrase.report.PhraseReportRepository;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.domain.user.UserRs;
import com.dsm.fox.global.exception.BasicException;
import com.dsm.fox.global.exception.exceptions.PhraseNotFoundException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhraseReportService {
    private final PhraseReportRepository reportRepository;
    private final PhraseRepository phraseRepository;
    private final UserRepository userRepository;

    public void phraseReport(int phraseId, String content) {
        Phrase phrase = phraseRepository.findById(phraseId).orElseThrow(PhraseNotFoundException::new);
        int id = 1; // token에서 빼내온 user id
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(reportRepository.existsByUserIdAndPhraseId(user.getId(), phrase.getId())) {
            throw new BasicException("신고는 두 번 이상할 수 없습니다.",400);
        }

        reportRepository.save(
                PhraseReport.builder()
                        .content(content)
                        .phrase(phrase)
                        .user(user).build()
        );
    }

    public List<PhraseReportRs> getPhraseList(Pageable pageable) {
        List<PhraseReportRs> phrases = reportRepository.findAll(pageable)
                .stream().map(phrase ->
                        PhraseReportRs.builder()
                                .id(phrase.getId())
                                .content(phrase.getContent())
                                .user(UserRs.builder()
                                        .id(phrase.getUser().getId())
                                        .name(phrase.getUser().getName())
                                        .build()).build()).collect(Collectors.toList());
        return phrases;
    }
}
