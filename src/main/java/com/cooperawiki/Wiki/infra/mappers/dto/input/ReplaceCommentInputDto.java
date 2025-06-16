package com.cooperawiki.Wiki.infra.mappers.dto.input;

import java.util.UUID;

public record ReplaceCommentInputDto(Long authorId, UUID commentId, String replace) {
    
}
