package com.example.kotlinserver.member

import com.example.kotlinserver.member.dto.MemberResponse
import com.example.kotlinserver.member.dto.SignUpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping
    fun signUp(@RequestBody dto: SignUpRequest): ResponseEntity<Void> {
        memberService.save(dto)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping
    fun searchList(@RequestParam(value = "") nickname: String): ResponseEntity<List<MemberResponse>> {
        return ResponseEntity.ok(memberService.findAllByNicknameContaining(nickname));
    }
}