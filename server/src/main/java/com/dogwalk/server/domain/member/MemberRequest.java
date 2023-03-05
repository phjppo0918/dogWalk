package com.dogwalk.server.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    @NotBlank
    private String username;
    @NotBlank
    @Setter
    private String password;
    @NotBlank
    private String petName;
}
