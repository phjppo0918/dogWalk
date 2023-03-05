package com.dogwalk.server.domain.member;

import com.dogwalk.server.global.EntityLoader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class MemberService implements EntityLoader<Member, Long> {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;
    @Override
    public Member getEntity(final Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public void create(final MemberRequest dto) {
        checkDuplicate(dto.getUsername());
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        memberRepository.save(memberMapper.toEntity(dto));
    }

    private void checkDuplicate(String username) {
        if(memberRepository.existsByUsername(username)) {
            throw new EntityExistsException();
        }
    }
}
