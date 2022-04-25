package com.dsm.fox.domain.phrase.rqrs;


import com.dsm.fox.domain.user.UserRs;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PhraseReportRs {
    private final int id;
    private final String content;
    private final UserRs user;
}
