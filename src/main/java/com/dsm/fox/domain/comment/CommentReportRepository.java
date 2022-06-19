package com.dsm.fox.domain.comment;

import org.springframework.data.repository.CrudRepository;

public interface CommentReportRepository extends CrudRepository<Comment, Integer> {
    void deleteAllByPostId(int id);
}
