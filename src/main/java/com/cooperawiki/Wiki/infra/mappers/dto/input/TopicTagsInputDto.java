package com.cooperawiki.Wiki.infra.mappers.dto.input;

import java.util.UUID;

public record TopicTagsInputDto(UUID topicId, Long tagId) {
    
}
