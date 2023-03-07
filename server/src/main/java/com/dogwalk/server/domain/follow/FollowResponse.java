package com.dogwalk.server.domain.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class FollowResponse {
    private final Long memberId;
    private final List<FollowElementResponse> follows;
}
