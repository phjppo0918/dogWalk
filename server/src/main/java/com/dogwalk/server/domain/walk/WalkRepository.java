package com.dogwalk.server.domain.walk;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalkRepository extends JpaRepository<Walk, Long> {
    List<Walk> findAllByMemberId(Long memberId);
}
