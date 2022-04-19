package com.dsm.fox.domain.post.controller;

import com.dsm.fox.domain.post.rqrs.PostReportRq;
import com.dsm.fox.domain.post.service.PostReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostReportController {
    private final PostReportService reportService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post/report")
    public void postReport(@RequestBody PostReportRq reportRq) {
        reportService.postReport(reportRq.getContent());
    }
}