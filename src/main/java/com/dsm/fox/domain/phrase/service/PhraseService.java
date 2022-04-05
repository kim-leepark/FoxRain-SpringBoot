package com.dsm.fox.domain.phrase.service;

import com.dsm.fox.domain.phrase.Phrase;
import com.dsm.fox.domain.phrase.PhraseRepository;
import com.dsm.fox.domain.phrase.rqrs.PhraseCreateRq;
import com.dsm.fox.domain.phrase.rqrs.PhraseRs;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhraseService {
    private final PhraseRepository phraseRepository;
    private final UserRepository userRepository;

    public void phraseRegistration(PhraseCreateRq rq) {

        int id = 1; // token에서 빼내온 user id
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        phraseRepository.save(
                Phrase.builder()
                    .content(rq.getContent())
                    .man(rq.getMan(user.getName()))
                    .user(user)
                    .build()
        );
    }

    public PhraseRs randomPhrase() {
        Phrase phrase = phraseRepository.findRandomPhrase();
        return PhraseRs.builder()
                .id(phrase.getId())
                .content(phrase.getContent())
                .man(phrase.getMan()).build();
    }
}
