package com.dsm.fox.domain.post.report;

import com.dsm.fox.domain.phrase.report.PhraseReport;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostReportRepository extends CrudRepository<PostReport, Integer> {
    boolean existsByUserIdAndPostId(int user, int post);
    List<PostReport> findAllByPost_Id(int postId,Pageable pageable);
}
