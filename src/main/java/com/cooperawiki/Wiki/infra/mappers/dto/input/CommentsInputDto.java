package com.cooperawiki.Wiki.infra.mappers.dto.input;

import java.util.UUID;

public record CommentsInputDto(Long authorId, UUID topicId, String title, String content) {
    
}
