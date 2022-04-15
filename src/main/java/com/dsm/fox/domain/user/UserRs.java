package com.dsm.fox.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class UserRs {
    private final int id;
    private final String name;
}
