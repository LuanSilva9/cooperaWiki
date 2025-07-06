package com.cooperawiki.Wiki.infra.mappers.dto.input;

import java.util.UUID;

import com.cooperawiki.Wiki.domain.enums.TypeContent;

public record ContentInputDto(UUID head, Long authorId, Long companyId, String title, String contentMarkdown, TypeContent typeContent) { }