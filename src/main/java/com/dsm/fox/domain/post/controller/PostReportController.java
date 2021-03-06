package com.dsm.fox.domain.post.controller;

import com.dsm.fox.domain.phrase.rqrs.ReportReasonRs;
import com.dsm.fox.domain.post.rqrs.PostReportRq;
import com.dsm.fox.domain.post.rqrs.ReportPostRs;
import com.dsm.fox.domain.post.service.PostReportService;
import com.dsm.fox.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostReportController {
    private final PostReportService reportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post/{id}/report")
    public void postReport(@AuthenticationPrincipal User user, @PathVariable("id") int postId, @RequestBody PostReportRq reportRq) {
        reportService.postReport(reportRq.getContent(), postId, user);
    }

    @GetMapping("/post/{id}/report/reasons")
    public List<ReportReasonRs> postReportReasonList(@PathVariable("id") int postId) {
        return reportService.getPostReportReason(postId);
    }

    @GetMapping("/report/posts")
    public List<ReportPostRs> reportPosts(Pageable pageable) {
        return reportService.getReportPosts(pageable);
    }
}