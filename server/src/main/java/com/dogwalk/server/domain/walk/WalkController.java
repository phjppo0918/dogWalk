package com.dogwalk.server.domain.walk;

import com.dogwalk.server.domain.member.MemberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("walks")
public class WalkController {
    private final WalkService walkService;

    @PostMapping
    public ResponseEntity<Void> start() {
        walkService.start();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("end/{id}")
    public ResponseEntity<Void> end(@PathVariable Long id) {
        walkService.end(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("my")
    public ResponseEntity<List<WalkResponse>> getMyWalks() {
        return ResponseEntity.ok(walkService.findAllMyWalks());
    }

    @GetMapping
    public ResponseEntity<List<WalkResponse>> getByMemberId(@RequestParam("member-id")Long memberId) {
        return ResponseEntity.ok(walkService.findAllByMemberId(memberId));
    }


}
