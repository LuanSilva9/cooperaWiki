package com.cooperawiki.Wiki.infra.mappers.dto.input;

public record TopicInputDto(Long authorId, Long companyId, String title, String contentMarkdown) {
    
}
