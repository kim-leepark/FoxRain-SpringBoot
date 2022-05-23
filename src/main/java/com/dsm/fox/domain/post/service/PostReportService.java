package com.dsm.fox.domain.post.service;

import com.dsm.fox.domain.phrase.rqrs.ReportReasonRs;
import com.dsm.fox.domain.post.Post;
import com.dsm.fox.domain.post.PostRepository;
import com.dsm.fox.domain.post.report.PostReport;
import com.dsm.fox.domain.post.report.PostReportRepository;
import com.dsm.fox.domain.post.rqrs.ReportPostRs;
import com.dsm.fox.domain.user.User;
import com.dsm.fox.domain.user.UserRepository;
import com.dsm.fox.domain.user.UserRs;
import com.dsm.fox.global.exception.BasicException;
import com.dsm.fox.global.exception.exceptions.PostNotFoundException;
import com.dsm.fox.global.exception.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostReportService {
    private final PostReportRepository reportRepository;
    private final PostRepository postRepository;

    @Transactional
    public void postReport(String content, int postId, User user) {
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
        post.increaseReport();
    }

    public List<ReportReasonRs> getPostReportReason(int postId) {
        return reportRepository.findAllByPost_Id(postId).stream().map(
                report -> ReportReasonRs.builder()
                                .id(postId)
                                .content(report.getContent())
                                .user(UserRs.builder()
                                        .id(report.getUser().getId())
                                        .name(report.getUser().getName())
                                        .build()).build()
        ).collect(Collectors.toList());
    }

    public List<ReportPostRs> getReportPosts(Pageable pageable) {
        return postRepository.findByReportNumIsNot(0, pageable).stream().map(
                post -> ReportPostRs.builder()
                                .id(post.getId())
                                .title(post.getTitle())
                                .content(post.getContent())
                                .reportNum(post.getReportNum())
                                .build()).collect(Collectors.toList());
    }
}
