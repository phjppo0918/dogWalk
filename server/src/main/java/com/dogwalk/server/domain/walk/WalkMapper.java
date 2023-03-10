package com.dogwalk.server.domain.walk;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WalkMapper {
    WalkResponse toResponse(Walk entity);
}
