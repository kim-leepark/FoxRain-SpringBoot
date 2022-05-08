package com.dsm.fox.domain.phrase.rqrs;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReportPhraseRs {
    private final int id;
    private final String content;
    private final String man;
    private final int reportNum;
}
