package com.dogwalk.server.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<Void> signup(@RequestBody @Valid final MemberRequest dto) {
        memberService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "id")
    public ResponseEntity<MemberResponse> getById(@RequestParam final Long id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping(params = "name")
    public ResponseEntity<List<MemberResponse>> getByName(@RequestParam final String name) {
        return ResponseEntity.ok(memberService.findByPetName(name));
    }
}
