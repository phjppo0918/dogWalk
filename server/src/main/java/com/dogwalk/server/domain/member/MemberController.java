package com.dogwalk.server.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> signup(@RequestBody @Valid MemberRequest dto) {
        memberService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
