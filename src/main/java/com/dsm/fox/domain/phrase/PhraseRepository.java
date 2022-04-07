package com.dsm.fox.domain.phrase;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PhraseRepository extends CrudRepository<Phrase, Integer> {
    @Query(value = "select * from phrase order by rand() limit 1",nativeQuery = true)
    Phrase findRandomPhrase();
}
