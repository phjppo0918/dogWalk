package com.dogwalk.server.domain.walk;

import com.dogwalk.server.domain.member.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class WalkResponse {
    private final Long id;
    private final MemberResponse member;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;
}
