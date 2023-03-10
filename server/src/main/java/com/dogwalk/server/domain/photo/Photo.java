package com.dogwalk.server.domain.photo;

import com.dogwalk.server.domain.walk.Walk;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Walk walk;
    @Column(nullable = false)
    private String url;

    public Photo(Walk walk, String url) {
        this.walk = walk;
        this.url = url;
    }
}
