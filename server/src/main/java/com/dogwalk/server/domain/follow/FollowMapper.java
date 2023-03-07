package com.dogwalk.server.domain.follow;

import com.dogwalk.server.domain.member.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FollowMapper {
    Follow toEntity(Member to, Member from);

    default FollowResponse toFollowingResponse(Long memberId, List<Follow> follows) {
        return new FollowResponse(memberId,
            follows.stream().map(this::toFollowingElement).toList());
    }
    default FollowResponse toFollowerResponse(Long memberId, List<Follow> follows) {
        return new FollowResponse(memberId,
                follows.stream().map(this::toFollowerElement).toList());
    }

    @Mapping(target = "member", source = "entity.to")
    FollowElementResponse toFollowingElement(Follow entity);
    @Mapping(target = "member", source = "entity.from")
    FollowElementResponse toFollowerElement(Follow entity);
}
