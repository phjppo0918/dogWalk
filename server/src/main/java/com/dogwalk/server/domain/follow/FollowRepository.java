package com.dogwalk.server.domain.follow;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findAllByToId(Long memberId);
    List<Follow> findAllByFromId(Long memberId);
}
