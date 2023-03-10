package com.dogwalk.server.domain.walk;

import com.dogwalk.server.domain.auth.LoginUserLoader;
import com.dogwalk.server.domain.member.Member;
import com.dogwalk.server.global.EntityLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WalkService implements EntityLoader<Walk, Long> {
    private final WalkRepository walkRepository;
    private final LoginUserLoader userLoader;
    private final WalkMapper walkMapper;

    @Transactional
    public void start() {
        Member loginUser = userLoader.getLoginUser();
        walkRepository.save(new Walk(loginUser));
    }
    @Transactional
    public void end(Long id) {
        getEntity(id).end();
    }

    public List<WalkResponse> findAllByMemberId(Long memberId) {
        return walkRepository.findAllByMemberId(memberId).stream().map(walkMapper::toResponse).toList();
    }

    @Override
    public Walk getEntity(Long id) {
        return walkRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<WalkResponse> findAllMyWalks() {
        return findAllByMemberId(userLoader.getLoginUser().getId());
    }
}
