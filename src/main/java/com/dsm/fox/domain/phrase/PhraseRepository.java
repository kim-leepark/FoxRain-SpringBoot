package com.dsm.fox.domain.phrase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PhraseRepository extends JpaRepository<Phrase, Integer> {
    @Query(value = "select * from phrase order by rand() limit 1",nativeQuery = true)
    Phrase findRandomPhrase();
}
