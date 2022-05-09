package com.dsm.fox.domain.post.rqrs;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportPostRs {
    private final int id;
    private final String title;
    private final String content;
    private final int reportNum;
}
