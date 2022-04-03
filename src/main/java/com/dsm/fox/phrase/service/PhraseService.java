package com.dsm.fox.phrase.service;

import com.dsm.fox.phrase.Phrase;
import com.dsm.fox.phrase.PhraseRepository;
import com.dsm.fox.phrase.rsrq.PhraseCreateRq;
import com.dsm.fox.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;

    public void phraseRegistration(PhraseCreateRq rq) {
        User user = User.builder().build(); // 예외처리
        String userId = "user id";
        phraseRepository.save(
                Phrase.builder()
                    .content(rq.getContent())
                    .man(rq.getMan(userId))
                    .user(user)
                    .build()
        );
    }
}
