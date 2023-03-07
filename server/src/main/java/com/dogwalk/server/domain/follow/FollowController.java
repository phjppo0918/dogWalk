package com.dogwalk.server.domain.follow;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("follows")
public class FollowController {
    private final FollowService followService;

    //내 팔로잉 목록 TODO
    @GetMapping("my-following")
    public ResponseEntity<FollowResponse> getMyFollowing() {
        return ResponseEntity.ok(followService.findAllMyFollowing());
    }

    @GetMapping("my-follower")
    public ResponseEntity<FollowResponse> getMyFollower() {
        return ResponseEntity.ok(followService.findAllMyFollower());
    }

    @GetMapping("following")
    public ResponseEntity<FollowResponse> getFollowing(@RequestParam(name = "member-id") Long memberId) {
        return ResponseEntity.ok(followService.findAllFollowingByMemberId(memberId));
    }

    @GetMapping("follower")
    public ResponseEntity<FollowResponse> getFollower(@RequestParam(name = "member-id") Long memberId) {
        return ResponseEntity.ok(followService.findAllFollowerByMemberId(memberId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid final FollowRequest dto) {
        followService.follow(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        followService.unFollow(id);
        return ResponseEntity.noContent().build();
    }

}
