package com.dsm.fox.domain.post;

import com.dsm.fox.domain.post.rqrs.PostRs;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Integer> {
    List<PostRs> findAll(Pageable pageable);
    List<Post> findByReportNumIsNot(int reportNot, Pageable pageable);

    Optional<Post> deleteById(int integer);
}
