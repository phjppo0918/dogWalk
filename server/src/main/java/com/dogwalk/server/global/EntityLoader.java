package com.dogwalk.server.global;

public interface EntityLoader<T, ID> {
    T getEntity(final ID id);
}
