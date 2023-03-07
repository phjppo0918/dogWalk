package com.dogwalk.server.domain.auth;

import com.dogwalk.server.domain.member.Member;

public interface LoginUserLoader {
    Member getLoginUser();
}
