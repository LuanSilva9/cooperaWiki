package com.cooperawiki.Wiki.infra.mappers.dto.input;

import com.cooperawiki.Wiki.domain.enums.TypeContent;

public record RelevanceInputDto(Long userId, TypeContent targetType, Boolean liked) {
    
}
