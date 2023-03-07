package com.dogwalk.server.domain.follow;

import com.dogwalk.server.domain.auth.LoginUserLoader;
import com.dogwalk.server.domain.member.Member;
import com.dogwalk.server.global.EntityLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final EntityLoader<Member, Long> memberLoader;
    private final LoginUserLoader userLoader;
    private final FollowMapper followMapper;

    @Transactional
    public void follow(final FollowRequest dto) {
        Member to = userLoader.getLoginUser();
        Member from = memberLoader.getEntity(dto.getMemberId());
        followRepository.save(followMapper.toEntity(to, from));
    }

    public FollowResponse findAllMyFollowing() {
        Long userId = userLoader.getLoginUser().getId();
        List<Follow> follows = followRepository.findAllByToId(userId);
        return followMapper.toFollowingResponse(userId, follows);
    }

    public FollowResponse findAllMyFollower() {
        Long userId = userLoader.getLoginUser().getId();
        List<Follow> follows = followRepository.findAllByFromId(userId);
        return followMapper.toFollowerResponse(userId, follows);
    }

    public FollowResponse findAllFollowingByMemberId(Long memberId) {
        List<Follow> follows = followRepository.findAllByToId(memberId);
        return followMapper.toFollowingResponse(memberId, follows);
    }

    public FollowResponse findAllFollowerByMemberId(Long memberId) {
        List<Follow> follows = followRepository.findAllByFromId(memberId);
        return followMapper.toFollowerResponse(memberId, follows);
    }

    @Transactional
    public void unFollow(Long id) {
        followRepository.deleteById(id);
    }
}
