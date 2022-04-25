package com.dsm.fox.domain.post.controller;

import com.dsm.fox.domain.post.rqrs.PostReportRq;
import com.dsm.fox.domain.post.service.PostReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostReportController {
    private final PostReportService reportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post/{id}/report")
    public void postReport(@PathVariable("id") int postId, @RequestBody PostReportRq reportRq) {
        reportService.postReport(reportRq.getContent(), postId);
    }

    @GetMapping("/post/{id}/reports")
    public void postReportList(@PathVariable("id") int postId, Pageable pageable) {
        reportService.getPostReports(postId, pageable);
    }
}