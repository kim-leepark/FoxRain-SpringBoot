package com.dsm.fox.domain.post.rqrs;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class PostRs {
    private final String title;
    private final String content;
    private final int userId;
    private final LocalDate createdAt;
}
