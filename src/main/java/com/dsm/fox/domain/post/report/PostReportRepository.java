package com.dsm.fox.domain.post.report;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostReportRepository extends CrudRepository<PostReport, Integer> {
    boolean existsByUserIdAndPostId(int user, int post);
    List<PostReport> findAllByPost_Id(int postId);
    void deleteAllByPostId(int postId);
}
