package com.dsm.fox.domain.admin.service;

import com.dsm.fox.domain.comment.CommentReportRepository;
import com.dsm.fox.domain.post.PostRepository;
import com.dsm.fox.domain.post.report.PostReportRepository;
import com.dsm.fox.global.exception.exceptions.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminDeleteService {
    private final PostRepository postRepository;
    private final PostReportRepository postReportRepository;
    private final CommentReportRepository commentReportRepository;

    @Transactional
    public void postDelete(int id) {
        if(!postRepository.existsById(id)) {
            throw new PostNotFoundException();
        }
        commentReportRepository.deleteAllByPostId(id);
        postReportRepository.deleteAllByPostId(id);
        postRepository.deleteById(id);
    }

}
