package com.dsm.fox.domain.post.service;

import com.dsm.fox.domain.post.Post;
import com.dsm.fox.domain.post.PostRepository;
import com.dsm.fox.domain.post.report.PostReport;
import com.dsm.fox.domain.post.report.PostReportRepository;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.global.exception.BasicException;
import com.dsm.fox.global.exception.exceptions.PostNotFoundException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostReportService {
    private final PostReportRepository reportRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void postReport(String content, int postId) {
        int id = 1;
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(reportRepository.existsByUserIdAndPostId(user.getId(), postId)) {
            throw new BasicException("신고는 두 번 이상할 수 없습니다.",400);
        }
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        reportRepository.save(
                PostReport.builder()
                    .content(content)
                    .user(user)
                    .post(post).build()
        );
    }
}
