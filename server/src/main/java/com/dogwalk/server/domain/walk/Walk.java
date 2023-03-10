package com.dogwalk.server.domain.walk;

import com.dogwalk.server.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Walk {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @Column(nullable = false)
    private LocalDateTime startAt;
    private LocalDateTime endAt;

    public Walk(Member member) {
        this.member = member;
        this.startAt = LocalDateTime.now();
    }
    public void end() {
        endAt = LocalDateTime.now();
    }
}
