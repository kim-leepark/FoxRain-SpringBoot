package com.dsm.fox.domain.post.rqrs;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PostRs {
    private final String title;
    private final String content;
    private final int userId;
    private final LocalDate createdAt;
    private final boolean isReport;
}

//인녕 종은아 반가워 딴짓하지말고 공부 열심히해 나중에 ㄱㅌㄷㅈㅈ 가자!