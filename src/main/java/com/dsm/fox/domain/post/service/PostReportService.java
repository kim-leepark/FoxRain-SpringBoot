package com.dsm.fox.domain.post.service;

import com.dsm.fox.domain.post.report.PostReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostReportService {
    private final PostReportRepository reportRepository;

    public void postReport(String reportContent) {


    }
}
