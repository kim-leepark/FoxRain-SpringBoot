package com.dsm.fox.domain.phrase.report;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhraseReportRepository extends CrudRepository<PhraseReport, Integer> {
    boolean existsByUserIdAndPhraseId(int user, int phrase);
    List<PhraseReport> findAllByPhraseId(int id);
}
