package com.dsm.fox.domain.phrase;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhraseRepository extends CrudRepository<Phrase, Integer> {
    @Query(value = "select * from phrase order by rand() limit 1",nativeQuery = true)
    Phrase findRandomPhrase();
    List<Phrase> findByReportNumIsNot(Pageable pageable, int reportNot);
}
