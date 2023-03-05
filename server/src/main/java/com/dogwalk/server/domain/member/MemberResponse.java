package com.dogwalk.server.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberResponse {
    private final Long id;
    private final String petName;
}
