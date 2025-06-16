package com.cooperawiki.Wiki.infra.mappers.dto.input;

import com.cooperawiki.Wiki.domain.enums.Theme;

public record ConfigInputDto(Theme theme, Boolean readOnly, String logo) {
    
}
