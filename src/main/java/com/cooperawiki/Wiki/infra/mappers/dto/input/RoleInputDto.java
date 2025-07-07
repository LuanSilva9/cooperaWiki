package com.cooperawiki.Wiki.infra.mappers.dto.input;

import com.cooperawiki.Wiki.domain.enums.TypeRoleAccess;

public record RoleInputDto(Long companyId, String role, TypeRoleAccess typeRoleAccess) {
    
}
