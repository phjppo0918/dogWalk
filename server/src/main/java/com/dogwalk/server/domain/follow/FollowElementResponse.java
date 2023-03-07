package com.dogwalk.server.domain.follow;

import com.dogwalk.server.domain.member.MemberResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FollowElementResponse {
    private final Long id;
    private final MemberResponse member;
}
