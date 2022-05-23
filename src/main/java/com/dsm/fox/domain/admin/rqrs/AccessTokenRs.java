package com.dsm.fox.domain.admin.rqrs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class AccessTokenRs {
    private String accessToken;
}
