package com.dogwalk.server.global;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IdResponse<T> {
    private final T id;
}
